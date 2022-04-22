package com.cat_breeds.common.breed_list.breed_list_data

import com.cat_breeds.cat_api.CatApi
import com.cat_breeds.common.breed_list.breed_list_domain.data.BreedListRemoteRepository
import com.cat_breeds.common.breed_list.breed_list_domain.model.BreedListItem

internal class BreedListRemoteRepositoryImpl(
    private val catApi: CatApi,
) : BreedListRemoteRepository {

    override suspend fun requestBreeds(): List<BreedListItem> {
        return catApi.requestCatBreeds()
            .map { BreedListItem(it.id, it.name, it.image?.url) }
    }
}