package com.cat_breeds.common.breed_info.breed_info_ui.mvi.store

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.cat_breeds.common.breed_info.breed_info_domain.BreedInfoInteractor
import com.cat_breeds.common.breed_info.breed_info_domain.model.BreedInfo
import com.cat_breeds.common.breed_info.breed_info_ui.BreedUiInfo
import com.cat_breeds.common.breed_info.breed_info_ui.mvi.BreedInfoIntent
import com.cat_breeds.common.breed_info.breed_info_ui.mvi.BreedInfoState
import com.cat_breeds.utils.DispatcherProvider
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

private typealias BreedInfoIntentExecutorCoroutineExecutor = CoroutineExecutor<
        BreedInfoIntent,
        Unit,
        BreedInfoState,
        BreedInfoMessage,
        BreedInfoLabel,
        >

internal class BreedInfoIntentExecutorImpl(
    private val breedId: String,
    private val breedInfoInteractor: BreedInfoInteractor,
    dispatcherProvider: DispatcherProvider,
) : BreedInfoIntentExecutorCoroutineExecutor(dispatcherProvider.main), BreedInfoIntentExecutor {

    override fun executeAction(action: Unit, getState: () -> BreedInfoState) {
        breedInfoInteractor.observeBreedInfo(breedId)
            .map { breedInfo: BreedInfo? ->
                BreedInfoMessage.InfoChanged(breedInfo?.run {
                    BreedUiInfo(name, imageUrl, origin, description, temperament)
                })
            }
            .onEach {
                dispatch(BreedInfoMessage.LoadingStateChanged(false))
                dispatch(it)
            }
            .onStart { dispatch(BreedInfoMessage.LoadingStateChanged(true)) }
            .launchIn(scope)
    }

    override fun executeIntent(intent: BreedInfoIntent, getState: () -> BreedInfoState) {
        when (intent) {
            BreedInfoIntent.OnCloseClicked -> publish(BreedInfoLabel.Close)
        }
    }
}