package com.cat_breeds.common.breed_list.breed_list_domain

import com.cat_breeds.common.breed_list.breed_list_domain.model.BreedListItem
import kotlinx.coroutines.flow.Flow

interface BreedListInteractor {
    suspend fun initBreeds()
    fun observeBreeds(): Flow<List<BreedListItem>>
    suspend fun forceUpdateBreeds()
}