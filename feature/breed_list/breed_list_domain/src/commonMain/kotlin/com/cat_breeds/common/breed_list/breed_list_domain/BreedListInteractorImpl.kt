package com.cat_breeds.common.breed_list.breed_list_domain

import com.cat_breeds.common.breed.breed_domain.BreedInteractor
import com.cat_breeds.common.breed_list.breed_list_domain.model.BreedListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class BreedListInteractorImpl(
    private val breedInteractor: BreedInteractor,
) : BreedListInteractor {
    override fun observeBreeds(): Flow<List<BreedListItem>> {
        return breedInteractor.observeBreeds()
            .map { breeds ->
                breeds.map { breed ->
                    BreedListItem(
                        id = breed.id,
                        name = breed.name,
                        imageUrl = breed.imageUrl,
                    )
                }
            }
    }

    override suspend fun initBreeds() {
        breedInteractor.initBreeds()
    }

    override suspend fun forceUpdateBreeds() {
        breedInteractor.forceUpdateBreeds()
    }
}