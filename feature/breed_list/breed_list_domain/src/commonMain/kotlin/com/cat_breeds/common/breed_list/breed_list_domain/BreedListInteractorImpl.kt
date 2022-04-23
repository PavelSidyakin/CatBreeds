package com.cat_breeds.common.breed_list.breed_list_domain

import com.cat_breeds.common.breed_list.breed_list_domain.data.BreedListRemoteRepository
import com.cat_breeds.common.breed_list.breed_list_domain.model.BreedListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class BreedListInteractorImpl(
    private val breedListRemoteRepository: BreedListRemoteRepository,
) : BreedListInteractor {
    override fun observeBreeds(): Flow<List<BreedListItem>> {
        return flow {
            try {
                emit(breedListRemoteRepository.requestBreeds())
            } catch (th: Throwable) {
                throw th
            }
        }
    }
}