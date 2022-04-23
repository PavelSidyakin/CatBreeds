package com.cat_breeds.common.breed_list.breed_list_ui

import com.arkivanov.decompose.value.Value
import com.cat_breeds.common.breed_list.breed_list_ui.mvi.BreedListState
import com.cat_breeds.common.breed_list.breed_list_ui.mvi.store.BreedListLabel
import com.cat_breeds.utils.mvi.ObservableValue

interface BreedListComponent {
    val models: Value<BreedListState>

    fun setOnLabelListener(listener: (label: BreedListLabel) -> Unit)

    fun onBreedClicked(id: String)

    fun onLaunch()
    fun onDispose()

    sealed interface Output {
        data class NavigateToBreedInfo(val breedId: String): Output
    }
}
