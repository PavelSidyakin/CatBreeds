package com.cat_breeds.common.breed_info.breed_info_domain

import com.cat_breeds.common.breed_info.breed_info_domain.data.BreedInfoRemoteRepository
import com.cat_breeds.common.breed_info.breed_info_domain.model.BreedInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class BreedInfoInteractorImpl(
    private val breedInfoRepository: BreedInfoRemoteRepository,
) : BreedInfoInteractor {
    override fun observeBreedInfo(id: String): Flow<BreedInfo?> {
        return flow { emit(breedInfoRepository.requestBreedInfo(id)) }
    }
}