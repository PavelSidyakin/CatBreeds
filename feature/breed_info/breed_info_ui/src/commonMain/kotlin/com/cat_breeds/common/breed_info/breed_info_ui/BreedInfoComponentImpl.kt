package com.cat_breeds.common.breed_info.breed_info_ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.cat_breeds.common.breed_info.breed_info_ui.di.breedInfoUiDI
import com.cat_breeds.common.breed_info.breed_info_ui.mvi.BreedInfoIntent
import com.cat_breeds.common.breed_info.breed_info_ui.mvi.store.BreedInfoLabel
import com.cat_breeds.common.breed_info.breed_info_ui.mvi.BreedInfoState
import com.cat_breeds.common.breed_info.breed_info_ui.mvi.store.BreedInfoIntentExecutor
import com.cat_breeds.common.breed_info.breed_info_ui.mvi.store.BreedInfoReducer
import com.cat_breeds.common.breed_info.breed_info_ui.mvi.store.BreedInfoStore
import com.cat_breeds.utils.DispatcherProvider
import com.cat_breeds.utils.mvi.lifecycleCoroutineScope
import com.cat_breeds.utils.mvi.stateAsValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.kodein.di.instance

internal class BreedInfoComponentImpl(
    private val params: BreedInfoComponentParams,
    private val dispatcherProvider: DispatcherProvider,
) : BreedInfoComponent, ComponentContext by params.componentContext {

    private val store: BreedInfoStore = instanceKeeper.getStore {
        val breedListIntentExecutor: BreedInfoIntentExecutor by breedInfoUiDI.instance(arg = params.breedId)

        object : BreedInfoStore,
            Store<BreedInfoIntent, BreedInfoState, BreedInfoLabel> by DefaultStoreFactory().create(
                initialState = BreedInfoState(),
                bootstrapper = SimpleBootstrapper(Unit),
                executorFactory = { breedListIntentExecutor },
                reducer = BreedInfoReducer(),
            ) {}
    }

    private val scope: CoroutineScope = lifecycleCoroutineScope(dispatcherProvider.main)

    override val models: Value<BreedInfoState> = store.stateAsValue(scope)

    init {
        store.labels
            .onEach { handleLabel(it) }
            .launchIn(scope)
    }

    override fun onCloseClicked() {
        store.accept(BreedInfoIntent.OnCloseClicked)
    }

    private fun handleLabel(label: BreedInfoLabel) {
        when (label) {
            BreedInfoLabel.Close -> params.outputCallback(BreedInfoComponent.Output.Close)
        }.run {  }
    }
}