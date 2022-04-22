package com.cat_breeds.common.breed_info.breed_info_ui

data class BreedUiInfo(
    val name: String,
    val imageUrl: String?,
    val origin: String?,
    val description: String?,
    val temperament: String? = null,
)
