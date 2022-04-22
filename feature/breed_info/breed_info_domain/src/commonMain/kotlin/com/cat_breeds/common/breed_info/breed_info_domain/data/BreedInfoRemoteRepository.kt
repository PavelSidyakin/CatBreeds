package com.cat_breeds.common.breed_info.breed_info_domain.data

import com.cat_breeds.common.breed_info.breed_info_domain.model.BreedInfo

interface BreedInfoRemoteRepository {
    suspend fun requestBreedInfo(id: String): BreedInfo?
}