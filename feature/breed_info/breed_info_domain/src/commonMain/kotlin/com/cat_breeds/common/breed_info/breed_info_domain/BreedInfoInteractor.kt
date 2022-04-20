package com.cat_breeds.common.breed_info.breed_info_domain

import com.cat_breeds.common.breed_info.breed_info_domain.model.BreedInfo

interface BreedInfoInteractor {
    suspend fun requestBreedInfo(): BreedInfo
}