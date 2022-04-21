package com.cat_breeds.common.breed_list.breed_list_ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.ValueObserver
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.cat_breeds.common.breed_list.breed_list_ui.di.breedListUiDI
import com.cat_breeds.common.breed_list.breed_list_ui.mvi.BreedListIntent
import com.cat_breeds.common.breed_list.breed_list_ui.mvi.BreedListState
import com.cat_breeds.common.breed_list.breed_list_ui.mvi.store.BreedListIntentExecutor
import com.cat_breeds.common.breed_list.breed_list_ui.mvi.store.BreedListReducer
import com.cat_breeds.common.breed_list.breed_list_ui.mvi.store.BreedListStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.kodein.di.instance

internal class BreedListComponent(
    params: BreedListComponentParams,
) : BreedList, ComponentContext by params.componentContext {

    private val store: BreedListStore = instanceKeeper.getStore {
        val breedListIntentExecutor by breedListUiDI.instance<BreedListIntentExecutor>()
        object : BreedListStore,
            Store<BreedListIntent, BreedListState, Nothing> by DefaultStoreFactory().create(
                initialState = BreedListState(),
                bootstrapper = SimpleBootstrapper(Unit),
                executorFactory = { breedListIntentExecutor },
                reducer = BreedListReducer(),
            ) {}
    }

    private val scope = CoroutineScope(Dispatchers.Main)

    override val models: Value<BreedListState> = object : Value<BreedListState>() {
        private val jobs = mutableMapOf<ValueObserver<BreedListState>, Job>()

        override val value: BreedListState = store.state
        override fun subscribe(observer: ValueObserver<BreedListState>) {
            jobs += observer to store.states
                .onEach { observer(it) }
                .launchIn(scope)
        }

        override fun unsubscribe(observer: ValueObserver<BreedListState>) {
            jobs[observer]?.cancel()
            jobs -= observer
        }
    }

    override fun onBreedClicked(id: String) {
        store.accept(BreedListIntent.Selected(id))
    }
}