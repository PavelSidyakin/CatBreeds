package com.cat_breeds.common.breed.breed_data

import com.cat_breeds.cat_api.CatApi
import com.cat_breeds.common.breed.breed_domain.data.BreedRemoteRepository
import com.cat_breeds.common.breed.breed_domain.model.Breed
import com.cat_breeds.cat_api.model.Breed as RemoteBreed

internal class BreedRemoteRepositoryImpl(
    private val catApi: CatApi,
) : BreedRemoteRepository {

    override suspend fun requestBreeds(): List<Breed> {
        return catApi.requestCatBreeds()
            .map { it.toBreed() }
    }

    override suspend fun requestBreed(id: String): Breed? {
        return catApi.requestCatBreed(id)?.toBreed()
    }

    private fun RemoteBreed.toBreed(): Breed {
        return Breed(
            id = id,
            name = name,
            imageUrl = image?.url,
            origin = origin,
            description = description,
            temperament = temperament,
        )
    }

}
