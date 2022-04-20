package com.cat_breeds.common.breed_list.breed_list_ui.mvi.store

import com.arkivanov.mvikotlin.core.store.Store
import com.cat_breeds.common.breed_list.breed_list_ui.mvi.BreedListIntent
import com.cat_breeds.common.breed_list.breed_list_ui.mvi.BreedListState

internal interface BreedListStore: Store<BreedListIntent, BreedListState, Nothing>