package com.cat_breeds.common.breed_list.breed_list_ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.cat_breeds.common.breed_list.breed_list_ui.di.breedListUiDI
import com.cat_breeds.common.breed_list.breed_list_ui.mvi.BreedListIntent
import com.cat_breeds.common.breed_list.breed_list_ui.mvi.BreedListState
import com.cat_breeds.common.breed_list.breed_list_ui.mvi.store.BreedListIntentExecutor
import com.cat_breeds.common.breed_list.breed_list_ui.mvi.store.BreedListLabel
import com.cat_breeds.common.breed_list.breed_list_ui.mvi.store.BreedListReducer
import com.cat_breeds.common.breed_list.breed_list_ui.mvi.store.BreedListStore
import com.cat_breeds.utils.mvi.lifecycleCoroutineScope
import com.cat_breeds.utils.mvi.stateAsValue
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.kodein.di.instance

internal class BreedListComponentImpl(
    private val params: BreedListComponentParams,
) : BreedListComponent, ComponentContext by params.componentContext {

    private val store: BreedListStore = instanceKeeper.getStore {
        val breedListIntentExecutor by breedListUiDI.instance<BreedListIntentExecutor>()

        object : BreedListStore, Store<BreedListIntent, BreedListState, BreedListLabel> by DefaultStoreFactory().create(
            initialState = BreedListState(),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = { breedListIntentExecutor },
            reducer = BreedListReducer(),
        ) {}
    }

    private val scope = lifecycleCoroutineScope()

    private var eventListener: ((BreedListComponent.Event) -> Unit)? = null

    override val models: Value<BreedListState> by lazy { store.stateAsValue(scope) }

    init {
        store.labels
            .onEach { handleLabel(it) }
            .launchIn(scope)
    }

    override fun setOnEventListener(listener: (event: BreedListComponent.Event) -> Unit) {
        eventListener = listener
    }

    override fun onBreedClicked(id: String) {
        store.accept(BreedListIntent.OnBreedClicked(id))
    }

    private fun handleLabel(label: BreedListLabel) {
        when (label) {
            is BreedListLabel.NavigateToBreedInfo -> params.outputCallback(
                BreedListComponent.Output.NavigateToBreedInfo(label.breedId)
            )
        }.run { } // make exhaustive
    }
}