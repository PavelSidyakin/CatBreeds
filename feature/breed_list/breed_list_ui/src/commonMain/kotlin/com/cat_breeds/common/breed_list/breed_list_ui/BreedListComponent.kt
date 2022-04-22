package com.cat_breeds.common.breed_list.breed_list_ui

import com.arkivanov.decompose.value.Value
import com.cat_breeds.common.breed_list.breed_list_ui.mvi.BreedListState
import com.cat_breeds.common.breed_list.breed_list_ui.mvi.store.BreedListLabel
import com.cat_breeds.utils.mvi.ObservableValue

interface BreedListComponent {
    val models: Value<BreedListState>

    val labels: ObservableValue<BreedListLabel>

    fun onBreedClicked(id: String)

    sealed interface Output {
        data class NavigateToBreedInfo(val breedId: String): Output
    }
}
