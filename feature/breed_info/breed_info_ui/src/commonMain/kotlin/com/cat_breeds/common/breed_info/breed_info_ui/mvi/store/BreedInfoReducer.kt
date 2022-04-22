package com.cat_breeds.common.breed_info.breed_info_ui.mvi.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.cat_breeds.common.breed_info.breed_info_ui.mvi.BreedInfoState

internal class BreedInfoReducer: Reducer<BreedInfoState, BreedInfoMessage> {
    override fun BreedInfoState.reduce(msg: BreedInfoMessage): BreedInfoState {
        return when (msg) {
            is BreedInfoMessage.InfoChanged -> copy(breedInfo = msg.newBreedInfo)
            is BreedInfoMessage.LoadingStateChanged -> copy(isLoading = msg.isLoading)
        }
    }
}