package com.cat_breeds.common.breed.breed_domain.data

import com.cat_breeds.common.breed.breed_domain.model.Breed


interface BreedRemoteRepository {
    suspend fun requestBreeds(): List<Breed>
    suspend fun requestBreed(id: String): Breed?
}