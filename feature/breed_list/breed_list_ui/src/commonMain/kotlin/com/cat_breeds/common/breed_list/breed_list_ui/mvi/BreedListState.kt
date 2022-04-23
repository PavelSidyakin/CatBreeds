package com.cat_breeds.common.breed_list.breed_list_ui.mvi

import com.cat_breeds.common.breed_list.breed_list_ui.BreedListUiItem

data class BreedListState(
    val breeds: List<BreedListUiItem> = emptyList(),
    val isLoading: Boolean = false,
)