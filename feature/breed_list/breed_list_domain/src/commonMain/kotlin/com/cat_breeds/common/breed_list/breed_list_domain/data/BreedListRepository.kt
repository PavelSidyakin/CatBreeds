package com.cat_breeds.common.breed_list.breed_list_domain.data

import com.cat_breeds.common.breed_list.breed_list_domain.model.BreedListItem

interface BreedListRepository {
    suspend fun requestBreeds(): List<BreedListItem>
}