package com.cat_breeds.common.breed_list.breed_list_ui.mvi.store

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.cat_breeds.common.breed_list.breed_list_domain.BreedListInteractor
import com.cat_breeds.common.breed_list.breed_list_domain.model.BreedListItem
import com.cat_breeds.common.breed_list.breed_list_ui.BreedListUiItem
import com.cat_breeds.common.breed_list.breed_list_ui.mvi.BreedListIntent
import com.cat_breeds.common.breed_list.breed_list_ui.mvi.BreedListState
import com.cat_breeds.utils.DispatcherProvider
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

private typealias BreedListIntentExecutorCoroutineExecutor = CoroutineExecutor<
        BreedListIntent,
        Unit,
        BreedListState,
        BreedListMessage,
        BreedListLabel,
        >

internal class BreedListIntentExecutorImpl(
    private val breedListInteractor: BreedListInteractor,
    dispatcherProvider: DispatcherProvider,
) : BreedListIntentExecutorCoroutineExecutor(dispatcherProvider.main), BreedListIntentExecutor {

    private var observeBreedsJob: Job? = null

    override fun executeAction(action: Unit, getState: () -> BreedListState) {
        observeBreeds()
        scope.launch {
            try {
                breedListInteractor.initBreeds()
            } catch (th: Throwable) {
                publish(BreedListLabel.ShowErrorMessage)
            }
        }
    }

    override fun executeIntent(intent: BreedListIntent, getState: () -> BreedListState) {
        when (intent) {
            is BreedListIntent.OnBreedClicked -> publish(BreedListLabel.NavigateToBreedInfo(intent.id))
            BreedListIntent.OnRefresh -> forceUpdateBreeds()
        }
    }

    private fun observeBreeds() {
        observeBreedsJob?.cancel()
        observeBreedsJob = breedListInteractor.observeBreeds()
            .map { list -> list.map { it.toBreedListUiItem() } }
            .map { BreedListMessage.ListChanged(it) }
            .onEach { listChangedMessage: BreedListMessage.ListChanged ->
                dispatch(listChangedMessage)
                dispatch(BreedListMessage.LoadingStateChanged(false))
            }
            .catch {
                publish(BreedListLabel.ShowErrorMessage)
                dispatch(BreedListMessage.LoadingStateChanged(false))
            }
            .onStart { dispatch(BreedListMessage.LoadingStateChanged(true)) }
            .launchIn(scope)
    }

    private fun BreedListItem.toBreedListUiItem(): BreedListUiItem {
        return BreedListUiItem(id, name, imageUrl)
    }

    private fun forceUpdateBreeds() {
        scope.launch {
            dispatch(BreedListMessage.LoadingStateChanged(true))
            try {
                breedListInteractor.forceUpdateBreeds()
            } catch (th: Throwable) {
                dispatch(BreedListMessage.LoadingStateChanged(false))
                publish(BreedListLabel.ShowErrorMessage)
            }
        }
    }
}