package com.cat_breeds.common.breed_info.breed_info_ui.mvi

import com.cat_breeds.common.breed_info.breed_info_ui.BreedUiInfo

data class BreedInfoState(
    val isLoading: Boolean = false,
    val breedInfo: BreedUiInfo? = null,
)