package com.cat_breeds.common.breed_list.breed_list_domain

import com.cat_breeds.common.breed_list.breed_list_domain.data.BreedListRepository
import com.cat_breeds.common.breed_list.breed_list_domain.model.BreedListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class BreedListInteractorImpl(
    private val breedInfoRepository: BreedListRepository,
) : BreedListInteractor {
    override fun observeBreeds(): Flow<List<BreedListItem>> {
        return flow {
            emit(breedInfoRepository.requestBreeds())
        }
    }
}