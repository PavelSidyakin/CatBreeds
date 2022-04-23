package com.cat_breeds.common.breed_info.breed_info_ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.cat_breeds.common.breed_info.breed_info_ui.di.breedInfoUiDI
import com.cat_breeds.common.breed_info.breed_info_ui.mvi.BreedInfoIntent
import com.cat_breeds.common.breed_info.breed_info_ui.mvi.BreedInfoLabel
import com.cat_breeds.common.breed_info.breed_info_ui.mvi.BreedInfoState
import com.cat_breeds.common.breed_info.breed_info_ui.mvi.store.BreedInfoIntentExecutor
import com.cat_breeds.common.breed_info.breed_info_ui.mvi.store.BreedInfoReducer
import com.cat_breeds.common.breed_info.breed_info_ui.mvi.store.BreedInfoStore
import com.cat_breeds.utils.mvi.stateAsValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.kodein.di.instance

internal class BreedInfoComponentImpl(
    private val params: BreedInfoComponentParams,
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

    private val scope: CoroutineScope = instanceKeeper.getOrCreate {
        object : InstanceKeeper.Instance {
            val scope = CoroutineScope(Dispatchers.Main)
            override fun onDestroy() {
                scope.cancel()
            }
        }
    }.scope

    private var labelsJob: Job? = null

    private var labelListener: ((BreedInfoLabel) -> Unit)? = null

    override val models: Value<BreedInfoState> = store.stateAsValue(scope)

    override fun onLaunch() {
        labelsJob = store.labels
            .onEach { label ->
                labelListener?.invoke(label)
                when (label) {
                    BreedInfoLabel.Close -> params.output(BreedInfoComponent.Output.Close)
                }
            }
            .launchIn(scope)
    }

    override fun onDispose() {
        labelsJob?.cancel()
    }

    override fun onCloseClicked() {
        store.accept(BreedInfoIntent.OnCloseClicked)
    }
}