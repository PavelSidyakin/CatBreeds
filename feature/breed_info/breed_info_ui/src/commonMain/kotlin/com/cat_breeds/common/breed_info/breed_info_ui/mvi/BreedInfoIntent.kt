package com.cat_breeds.common.breed_info.breed_info_ui.mvi

sealed interface BreedInfoIntent {
    object OnCloseClicked: BreedInfoIntent
}