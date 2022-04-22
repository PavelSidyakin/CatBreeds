package com.cat_breeds.common

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import com.cat_breeds.common.breed_info.breed_info_ui.BreedInfoComponent
import com.cat_breeds.common.breed_list.breed_list_ui.BreedListComponent

interface CatBreedsRoot {
    val routerState: Value<RouterState<*, ChildComponent>>

    sealed interface ChildComponent {
        data class BreedListChild(val component: BreedListComponent) : ChildComponent
        data class BreedInfoChild(val component: BreedInfoComponent) : ChildComponent
    }
}