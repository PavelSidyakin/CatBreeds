package com.cat_breeds.common.breed_info.breed_info_domain

import com.cat_breeds.common.breed_info.breed_info_domain.data.BreedInfoRepository
import com.cat_breeds.common.breed_info.breed_info_domain.model.BreedInfo

internal class BreedInfoInteractorImpl(
    private val breedInfoRepository: BreedInfoRepository,
) : BreedInfoInteractor {
    override suspend fun requestBreedInfo(): BreedInfo {
        return breedInfoRepository.requestBreedInfo()
    }
}