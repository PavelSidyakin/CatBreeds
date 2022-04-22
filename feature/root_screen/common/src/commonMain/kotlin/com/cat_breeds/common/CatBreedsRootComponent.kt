package com.cat_breeds.common

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.pop
import com.arkivanov.decompose.router.push
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.cat_breeds.common.breed_info.breed_info_ui.BreedInfoComponent
import com.cat_breeds.common.breed_info.breed_info_ui.BreedInfoComponentParams
import com.cat_breeds.common.breed_list.breed_list_ui.BreedListComponent
import com.cat_breeds.common.breed_list.breed_list_ui.BreedListComponentParams


internal class CatBreedsRootComponent(
    params: CatBreedsRootParams,
    private val breedListFactory: (BreedListComponentParams) -> BreedListComponent,
    private val breedInfoFactory: (BreedInfoComponentParams) -> BreedInfoComponent,
) : CatBreedsRoot, ComponentContext by params.componentContext {
    private val router = router<Configuration, CatBreedsRoot.ChildComponent>(
        initialStack = { listOf(Configuration.BreedList) },
        handleBackButton = true,
        childFactory = ::createChild,
    )

    private fun createChild(
        configuration: Configuration,
        componentContext: ComponentContext,
    ): CatBreedsRoot.ChildComponent {
        return when (configuration) {
            is Configuration.BreedInfo -> CatBreedsRoot.ChildComponent.BreedInfoChild(
                breedInfoFactory(BreedInfoComponentParams(componentContext, configuration.id, ::handleBreedInfoOutput))
            )
            is Configuration.BreedList -> CatBreedsRoot.ChildComponent.BreedListChild(
                breedListFactory(BreedListComponentParams(componentContext, ::handleBreedListOutput))
            )
        }
    }

    private fun handleBreedListOutput(output: BreedListComponent.Output) {
        when (output) {
            is BreedListComponent.Output.NavigateToBreedInfo -> {
                router.push(Configuration.BreedInfo(id = output.breedId))
            }
        }
    }

    private fun handleBreedInfoOutput(output: BreedInfoComponent.Output) {
        when (output) {
            BreedInfoComponent.Output.Close -> {
                router.pop()
            }
        }
    }

    override val routerState: Value<RouterState<*, CatBreedsRoot.ChildComponent>> = router.state

    private sealed class Configuration : Parcelable {
        @Parcelize
        object BreedList : Configuration()

        @Parcelize
        data class BreedInfo(val id: String) : Configuration()
    }
}