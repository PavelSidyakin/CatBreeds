package com.cat_breeds.common.breed_info.breed_info_data

import com.cat_breeds.common.breed_info.breed_info_domain.data.BreedInfoRepository
import com.cat_breeds.common.breed_info.breed_info_domain.model.BreedInfo

internal class BreedInfoRepositoryImpl : BreedInfoRepository {

    override suspend fun requestBreedInfo(): BreedInfo {
        return BreedInfo("test test")
    }
}