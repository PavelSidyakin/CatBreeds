package com.cat_breeds.common.breed_list.breed_list_ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
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
import com.cat_breeds.utils.mvi.stateAsValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
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

    private val scope: CoroutineScope = instanceKeeper.getOrCreate {
        object : InstanceKeeper.Instance {
            val scope = CoroutineScope(Dispatchers.Main)
            override fun onDestroy() {
                scope.cancel()
            }
        }
    }.scope

    private var labelsJob: Job? = null

    private var labelListener: ((BreedListLabel) -> Unit)? = null

    override val models: Value<BreedListState> by lazy { store.stateAsValue(scope) }

    override fun onLaunch() {
        labelsJob = store.labels
            .onEach { label ->
                labelListener?.invoke(label)
                when (label) {
                    is BreedListLabel.NavigateToBreedInfo -> params.outputCallback(
                        BreedListComponent.Output.NavigateToBreedInfo(
                            label.breedId,
                        )
                    )
                }
            }
            .launchIn(scope)
    }

    override fun onDispose() {
        labelsJob?.cancel()
    }

    override fun setOnLabelListener(listener: (label: BreedListLabel) -> Unit) {
        labelListener = listener
    }

    override fun onBreedClicked(id: String) {
        store.accept(BreedListIntent.OnBreedClicked(id))
    }
}