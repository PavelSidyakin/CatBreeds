package com.cat_breeds.common.breed_list.breed_list_data

import com.cat_breeds.common.breed_list.breed_list_domain.data.BreedListRepository
import com.cat_breeds.common.breed_list.breed_list_domain.model.BreedListItem

internal class BreedListRepositoryImpl : BreedListRepository {

    override suspend fun requestBreeds(): List<BreedListItem> {
        return listOf(BreedListItem("b1"), BreedListItem("b2"), BreedListItem("b3"))
    }
}