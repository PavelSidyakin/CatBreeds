package com.cat_breeds.common.breed.breed_domain.data

import com.cat_breeds.common.breed.breed_domain.model.Breed
import kotlinx.coroutines.flow.Flow

interface BreedLocalRepository {
    suspend fun hasBreeds(): Boolean
    fun observeBreeds(): Flow<List<Breed>>
    fun observeBreed(breedId: String): Flow<Breed>
    suspend fun selectBreed(breedId: String): Breed?
    suspend fun addBreeds(breeds: List<Breed>)
    suspend fun clearBreeds()
}
