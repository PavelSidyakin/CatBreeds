package com.cat_breeds.common

import com.arkivanov.decompose.value.Value
import com.cat_breeds.common.breed_info.breed_info_ui.BreedInfoComponent
import com.cat_breeds.common.breed_info.breed_info_ui.mvi.BreedInfoState

class FakeBreedInfoComponent(
    val breedId: String,
    val outputCallback: (BreedInfoComponent.Output) -> Unit,
) : BreedInfoComponent {

    override val models: Value<BreedInfoState>
        get() = TODO("Not used")

    override fun onCloseClicked() = Unit
}
