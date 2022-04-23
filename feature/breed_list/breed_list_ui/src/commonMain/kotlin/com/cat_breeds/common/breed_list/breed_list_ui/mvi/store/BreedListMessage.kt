package com.cat_breeds.common.breed_list.breed_list_ui.mvi.store

import com.cat_breeds.common.breed_list.breed_list_ui.BreedListUiItem

sealed interface BreedListMessage {
    data class ListChanged(val newBreedList: List<BreedListUiItem>): BreedListMessage
    data class LoadingStateChanged(val isLoading: Boolean): BreedListMessage
}