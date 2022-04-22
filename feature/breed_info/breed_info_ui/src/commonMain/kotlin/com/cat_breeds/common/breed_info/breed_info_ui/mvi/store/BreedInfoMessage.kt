package com.cat_breeds.common.breed_info.breed_info_ui.mvi.store

import com.cat_breeds.common.breed_info.breed_info_ui.BreedUiInfo

sealed interface BreedInfoMessage {
    data class InfoChanged(val newBreedInfo: BreedUiInfo?): BreedInfoMessage
    data class LoadingStateChanged(val isLoading: Boolean): BreedInfoMessage
}