package com.cat_breeds.common.breed.breed_domain

import com.cat_breeds.common.breed.breed_domain.data.BreedLocalRepository
import com.cat_breeds.common.breed.breed_domain.data.BreedRemoteRepository
import com.cat_breeds.common.breed.breed_domain.model.Breed
import kotlinx.coroutines.flow.Flow

internal class BreedInteractorImpl(
    private val breedRemoteRepository: BreedRemoteRepository,
    private val breedLocalRepository: BreedLocalRepository,
) : BreedInteractor {
    override fun observeBreeds(): Flow<List<Breed>> {
        return breedLocalRepository.observeBreeds()
    }

    override fun observeBreed(breedId: String): Flow<Breed> {
        return breedLocalRepository.observeBreed(breedId)
    }

    override suspend fun requestBreed(breedId: String): Breed? {
        val localBreed = breedLocalRepository.selectBreed(breedId)
        if (localBreed != null) {
            return localBreed
        }

        val remoteBreed = breedRemoteRepository.requestBreed(breedId)

        if (remoteBreed != null) {
            breedLocalRepository.addBreeds(listOf(remoteBreed))
            return remoteBreed
        }
        return null
    }

    override suspend fun forceUpdateBreeds() {
        val remoteBreeds = breedRemoteRepository.requestBreeds()
        breedLocalRepository.clearAndAddBreads(remoteBreeds)
    }

    override suspend fun initBreeds() {
        if (!breedLocalRepository.hasBreeds()) {
            breedLocalRepository.addBreeds(breedRemoteRepository.requestBreeds())
        }
    }
}
