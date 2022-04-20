package com.cat_breeds.common

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.cat_breeds.common.breed_list.breed_list_ui.BreedList
import com.cat_breeds.common.breed_list.breed_list_ui.BreedListComponentParams
import org.kodein.di.instance


class CatBreedsRootComponent internal constructor(
    componentContext: ComponentContext,
    private val breedListFactory: (ComponentContext) -> BreedList,
) : CatBreedsRoot, ComponentContext by componentContext {

    constructor(
        componentContext: ComponentContext,
    ) : this(
        componentContext = componentContext,
        breedListFactory = { childContext ->
            val breedList: BreedList by globalDI.instance(
                arg = BreedListComponentParams(
                    childContext
                )
            )
            breedList
        }
    )

    private val router = router<Configuration, CatBreedsRoot.ChildComponent>(
        initialConfiguration = Configuration.BreedList,
        handleBackButton = true,
        childFactory = ::createChild,
    )

    private fun createChild(configuration: Configuration, componentContext: ComponentContext): CatBreedsRoot.ChildComponent {
        return when (configuration) {
            is Configuration.BreedInfo -> TODO()
            Configuration.BreedList -> CatBreedsRoot.ChildComponent.BreedListChild(breedListFactory(componentContext))
        }
    }

    override val routerState: Value<RouterState<*, CatBreedsRoot.ChildComponent>>
        get() = TODO("Not yet implemented")

    private sealed interface Configuration: Parcelable {
        @Parcelize
        object BreedList: Configuration

        @Parcelize
        data class BreedInfo(val id: String): Configuration
    }
}