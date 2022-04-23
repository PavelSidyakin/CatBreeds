package com.cat_breeds.common.breed.breed_domain

import com.cat_breeds.common.breed.breed_domain.model.Breed
import kotlinx.coroutines.flow.Flow

interface BreedInteractor {
    fun observeBreeds(): Flow<List<Breed>>
    fun observeBreed(breedId: String): Flow<Breed>
    suspend fun requestBreed(breedId: String): Breed?
    suspend fun forceUpdateBreeds()
    suspend fun initBreeds()
}