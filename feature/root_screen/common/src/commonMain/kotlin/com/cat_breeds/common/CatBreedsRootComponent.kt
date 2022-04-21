package com.cat_breeds.common

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.cat_breeds.common.breed_list.breed_list_ui.BreedList
import com.cat_breeds.common.breed_list.breed_list_ui.BreedListComponentParams


internal class CatBreedsRootComponent(
    params: CatBreedsRootParams,
    private val breedListFactory: (BreedListComponentParams) -> BreedList,
) : CatBreedsRoot, ComponentContext by params.componentContext {

//    private val breedListFactory: (ComponentContext) -> BreedList = { childContext ->
//        val breedList: BreedList by globalDI.instance(
//            arg = BreedListComponentParams(
//                childContext,
//            )
//        )
//        breedList
//    }

    private val router = router<Configuration, CatBreedsRoot.ChildComponent>(
        initialStack = { listOf(Configuration.BreedList)},
        handleBackButton = true,
        childFactory = ::createChild,
    )

    private fun createChild(configuration: Configuration, componentContext: ComponentContext): CatBreedsRoot.ChildComponent {
        return when (configuration) {
            is Configuration.BreedInfo -> TODO()
            is Configuration.BreedList -> CatBreedsRoot.ChildComponent.BreedListChild(
                breedListFactory(BreedListComponentParams(componentContext))
            )
        }
    }

    override val routerState: Value<RouterState<*, CatBreedsRoot.ChildComponent>> = router.state

    private sealed class Configuration: Parcelable {
        @Parcelize
        object BreedList: Configuration()

        @Parcelize
        data class BreedInfo(val id: String): Configuration()
    }
}