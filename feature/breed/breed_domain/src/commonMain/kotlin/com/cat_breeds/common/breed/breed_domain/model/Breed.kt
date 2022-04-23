package com.cat_breeds.common.breed.breed_domain.model

data class Breed(
    val id: String,
    val name: String,
    val imageUrl: String?,
    val origin: String?,
    val description: String?,
    val temperament: String? = null,
)
