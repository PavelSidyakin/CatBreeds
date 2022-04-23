package com.cat_breeds.common.breed_list.breed_list_ui

import com.arkivanov.decompose.value.Value
import com.cat_breeds.common.breed_list.breed_list_ui.mvi.BreedListState

interface BreedListComponent {
    val models: Value<BreedListState>

    fun setOnEventListener(listener: (event: Event) -> Unit)

    fun onBreedClicked(id: String)
    fun onRefreshClicked()

    sealed interface Event {
        object Error: Event
    }

    sealed interface Output {
        data class NavigateToBreedInfo(val breedId: String): Output
    }
}
