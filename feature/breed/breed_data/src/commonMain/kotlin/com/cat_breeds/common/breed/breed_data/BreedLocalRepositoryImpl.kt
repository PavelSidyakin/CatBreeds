package com.cat_breeds.common.breed.breed_data

import com.cat_breeds.common.breed.breed_domain.data.BreedLocalRepository
import com.cat_breeds.common.breed.breed_domain.model.Breed
import com.cat_breeds.data.db.CatBreedsDatabase
import com.cat_breeds.utils.DispatcherProvider
import com.catbreeds.data.db.TCatBreed
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

internal class BreedLocalRepositoryImpl(
    private val db: CatBreedsDatabase,
    private val dispatcherProvider: DispatcherProvider,
) : BreedLocalRepository {

    override suspend fun hasBreeds(): Boolean {
        return withContext(dispatcherProvider.io) {
            db.catBreedsDatabaseQueries.hasBreeds().asFlow().mapToList().first().first()
        }
    }

    override fun observeBreeds(): Flow<List<Breed>> {
        return db.catBreedsDatabaseQueries.selectAllBreeds()
            .asFlow()
            .mapToList()
            .flowOn(dispatcherProvider.io)
            .map { dbBreeds: List<TCatBreed> ->
                dbBreeds.map { it.toBreed() }
            }
    }

    override suspend fun selectBreed(breedId: String): Breed? {
        return withContext(dispatcherProvider.io) {
            db.catBreedsDatabaseQueries.selectBreed(breedId).asFlow().mapToList().firstOrNull()?.firstOrNull()?.toBreed()
        }
    }

    override fun observeBreed(breedId: String): Flow<Breed> {
        return db.catBreedsDatabaseQueries.selectBreed(breedId)
            .asFlow()
            .mapToList()
            .flowOn(dispatcherProvider.io)
            .map { dbBreeds: List<TCatBreed> ->
                dbBreeds.map { it.toBreed() }.first()
            }
    }

    override suspend fun addBreeds(breeds: List<Breed>) {
        withContext(dispatcherProvider.io) {
            db.catBreedsDatabaseQueries.run {
                transaction {
                    breeds.forEach { breed ->
                        insertCatBreeds(breed.toTCatBreed())
                    }
                }
            }
        }
    }

    override suspend fun clearBreeds() {
        withContext(dispatcherProvider.io) {
            db.catBreedsDatabaseQueries.deleteCatBreeds()
        }
    }

    override suspend fun clearAndAddBreads(breeds: List<Breed>) {
        withContext(dispatcherProvider.io) {
            db.transaction {
                db.catBreedsDatabaseQueries.deleteCatBreeds()
                breeds.forEach { breed ->
                    db.catBreedsDatabaseQueries.insertCatBreeds(breed.toTCatBreed())
                }
            }
        }
    }

    private fun TCatBreed.toBreed(): Breed {
        return Breed(
            id = f_breedId,
            name = f_name,
            imageUrl = f_image_url,
            origin = f_origin,
            description = f_description,
            temperament = f_temperament,
        )
    }

    private fun Breed.toTCatBreed(): TCatBreed {
        return TCatBreed(
            f_breedId = id,
            f_name = name,
            f_image_url = imageUrl,
            f_description = description,
            f_origin = origin,
            f_temperament = temperament,
        )
    }
}
