package com.cat_breeds.common.breed_list.breed_list_ui.mvi.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.cat_breeds.common.breed_list.breed_list_ui.mvi.BreedListState

internal class BreedListReducer: Reducer<BreedListState, BreedListMessage> {
    override fun BreedListState.reduce(msg: BreedListMessage): BreedListState {
        return when (msg) {
            is BreedListMessage.ListChanged -> copy(breeds = msg.newBreedList)
            is BreedListMessage.LoadingStateChanged -> copy(isLoading = msg.isLoading)
        }
    }
}