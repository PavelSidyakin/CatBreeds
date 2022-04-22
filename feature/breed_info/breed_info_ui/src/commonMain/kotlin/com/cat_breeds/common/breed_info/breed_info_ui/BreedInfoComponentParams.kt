package com.cat_breeds.common.breed_info.breed_info_ui

import com.arkivanov.decompose.ComponentContext

data class BreedInfoComponentParams(
    val componentContext: ComponentContext,
    val breedId: String,
    val output: (BreedInfoComponent.Output) -> Unit,
)