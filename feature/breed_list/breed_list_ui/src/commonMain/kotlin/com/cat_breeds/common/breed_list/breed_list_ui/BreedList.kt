package com.cat_breeds.common.breed_list.breed_list_ui

import com.arkivanov.decompose.value.Value
import com.cat_breeds.common.breed_list.breed_list_ui.mvi.BreedListState

interface BreedList {
    val models: Value<BreedListState>

    fun onBreedClicked(id: String)
}
