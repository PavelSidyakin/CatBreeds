package com.cat_breeds.common.breed_info.breed_info_domain

import com.cat_breeds.common.breed_info.breed_info_domain.model.BreedInfo
import kotlinx.coroutines.flow.Flow

interface BreedInfoInteractor {
    fun observeBreedInfo(id: String): Flow<BreedInfo?>
}