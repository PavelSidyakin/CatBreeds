package com.cat_breeds.cat_api

import com.cat_breeds.cat_api.model.Breed

interface CatApi {
    suspend fun requestCatBreeds(): List<Breed>
}