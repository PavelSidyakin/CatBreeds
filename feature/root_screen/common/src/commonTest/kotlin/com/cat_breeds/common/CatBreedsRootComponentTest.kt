package com.cat_breeds.common

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.cat_breeds.common.breed_info.breed_info_ui.BreedInfoComponent
import com.cat_breeds.common.breed_list.breed_list_ui.BreedListComponent
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class CatBreedsRootComponentTest : FreeSpec() {
    private val root = CatBreedsRootComponent(
        params = CatBreedsRootParams(DefaultComponentContext(LifecycleRegistry().apply { resume() })),
        breedInfoFactory = { params -> FakeBreedInfoComponent(params.breedId, params.outputCallback) },
        breedListFactory = { params -> FakeBreedListComponent(params.outputCallback) },
    )

    private val CatBreedsRootComponent.activeChild: CatBreedsRoot.ChildComponent
        get() = routerState.value.activeChild.instance

    private val CatBreedsRoot.ChildComponent.component: Any
        get() = when (this) {
            is CatBreedsRoot.ChildComponent.BreedInfoChild -> component
            is CatBreedsRoot.ChildComponent.BreedListChild -> component
        }

    private val CatBreedsRoot.ChildComponent.asBreedList: FakeBreedListComponent
        get() = component as FakeBreedListComponent

    private val CatBreedsRoot.ChildComponent.asBreedInfo: FakeBreedInfoComponent
        get() = component as FakeBreedInfoComponent

    init {
        isolationMode = IsolationMode.InstancePerTest

        "When root is created" - {
            "then the breed list is displayed " {
                // Verify
                root.activeChild.shouldBeInstanceOf<CatBreedsRoot.ChildComponent.BreedListChild>()
            }
        }

        "When breed is selected" - {
            val breedId = "cwecwcwe"
            // Action
            root.activeChild.asBreedList.outputCallback(BreedListComponent.Output.NavigateToBreedInfo(breedId))

            "should display breed info" {
                // Verify
                root.activeChild.shouldBeInstanceOf<CatBreedsRoot.ChildComponent.BreedInfoChild>()
            }
            "should display selected breed" {
                // Verify
                root.activeChild.asBreedInfo.breedId shouldBe breedId
            }

            "When breed info is closed" - {
                // Action
                root.activeChild.asBreedInfo.outputCallback(BreedInfoComponent.Output.Close)

                "should display breed list" {
                    // Verify
                    root.activeChild.shouldBeInstanceOf<CatBreedsRoot.ChildComponent.BreedListChild>()
                }
            }
        }

    }
}
