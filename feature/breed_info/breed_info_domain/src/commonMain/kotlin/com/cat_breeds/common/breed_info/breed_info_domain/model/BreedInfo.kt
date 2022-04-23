package com.cat_breeds.common.breed_info.breed_info_domain.model

data class BreedInfo(
    val id: String,
    val name: String,
    val imageUrl: String?,
    val origin: String?,
    val description: String?,
    val temperament: String? = null,
)
