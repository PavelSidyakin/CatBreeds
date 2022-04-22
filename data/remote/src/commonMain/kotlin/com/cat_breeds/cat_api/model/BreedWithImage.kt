package com.cat_breeds.cat_api.model

import kotlinx.serialization.SerialName

data class BreedWithImage(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("url")
    val imageUrl: Image? = null,

    @SerialName("origin")
    val origin: String? = null,

    @SerialName("description")
    val description: String? = null,

    @SerialName("temperament")
    val temperament: String? = null,

    )
