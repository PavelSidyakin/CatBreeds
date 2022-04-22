package com.cat_breeds.common.breed_info.breed_info_data

import com.cat_breeds.cat_api.CatApi
import com.cat_breeds.common.breed_info.breed_info_domain.data.BreedInfoRemoteRepository
import com.cat_breeds.common.breed_info.breed_info_domain.model.BreedInfo

internal class BreedInfoRemoteRepositoryImpl(
    private val catApi: CatApi,
) : BreedInfoRemoteRepository {

    override suspend fun requestBreedInfo(id: String): BreedInfo? {
        return catApi.requestCatBreed(id)?.run {
            BreedInfo(name, image?.url, origin, description, temperament)
        }
    }
}
