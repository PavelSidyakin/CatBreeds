package com.cat_breeds.common.breed_list.breed_list_ui.mvi.store

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.cat_breeds.common.breed_list.breed_list_domain.BreedListInteractor
import com.cat_breeds.common.breed_list.breed_list_domain.model.BreedListItem
import com.cat_breeds.common.breed_list.breed_list_ui.BreedListUiItem
import com.cat_breeds.common.breed_list.breed_list_ui.mvi.BreedListIntent
import com.cat_breeds.common.breed_list.breed_list_ui.mvi.BreedListState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

private typealias BreedListIntentExecutorCoroutineExecutor = CoroutineExecutor<
        BreedListIntent,
        Unit,
        BreedListState,
        BreedListMessage,
        BreedListLabel,
        >

internal class BreedListIntentExecutorImpl(
    private val breedListInteractor: BreedListInteractor,
) : BreedListIntentExecutorCoroutineExecutor(), BreedListIntentExecutor {

    override fun executeAction(action: Unit, getState: () -> BreedListState) {
        breedListInteractor.observeBreeds()
            .map { list -> list.map { it.toBreedListUiItem() } }
            .map { BreedListMessage.ListChanged(it) }
            .onEach { dispatch(it) }
            .launchIn(scope)
    }

    override fun executeIntent(intent: BreedListIntent, getState: () -> BreedListState) {
        when (intent) {
            is BreedListIntent.OnBreedClicked -> publish(BreedListLabel.NavigateToBreedInfo(intent.id))
        }
    }

    private fun BreedListItem.toBreedListUiItem(): BreedListUiItem {
        return BreedListUiItem(id, name, imageUrl)
    }
}