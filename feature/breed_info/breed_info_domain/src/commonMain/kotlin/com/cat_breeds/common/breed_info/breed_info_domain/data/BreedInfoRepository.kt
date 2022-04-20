package com.cat_breeds.common.breed_info.breed_info_domain.data

import com.cat_breeds.common.breed_info.breed_info_domain.model.BreedInfo

interface BreedInfoRepository {
    suspend fun requestBreedInfo(): BreedInfo
}