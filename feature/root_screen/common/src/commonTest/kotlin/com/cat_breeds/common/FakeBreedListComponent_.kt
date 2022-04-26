package com.cat_breeds.common

import com.arkivanov.decompose.value.Value
import com.cat_breeds.common.breed_list.breed_list_ui.BreedListComponent
import com.cat_breeds.common.breed_list.breed_list_ui.mvi.BreedListState

class FakeBreedListComponent(val outputCallback: (BreedListComponent.Output) -> Unit) : BreedListComponent {

    override val models: Value<BreedListState>
        get() = TODO("Not used")

    override fun setOnEventListener(listener: (event: BreedListComponent.Event) -> Unit) = Unit

    override fun onBreedClicked(id: String) = Unit

    override fun onRefresh() = Unit
}
