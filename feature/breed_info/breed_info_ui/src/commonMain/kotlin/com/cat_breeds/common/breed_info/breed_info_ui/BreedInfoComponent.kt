package com.cat_breeds.common.breed_info.breed_info_ui

import com.arkivanov.decompose.value.Value
import com.cat_breeds.common.breed_info.breed_info_ui.mvi.BreedInfoState

interface BreedInfoComponent {
    val models: Value<BreedInfoState>

    fun onCloseClicked()

    fun onLaunch()
    fun onDispose()

    sealed interface Output {
        object Close: Output
    }
}
