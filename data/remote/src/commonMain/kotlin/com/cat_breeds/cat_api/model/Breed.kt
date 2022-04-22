package com.cat_breeds.cat_api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Breed(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("image")
    val image: Image? = null,

    @SerialName("origin")
    val origin: String? = null,

    @SerialName("description")
    val description: String? = null,

    @SerialName("temperament")
    val temperament: String? = null,
)
