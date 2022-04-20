package com.cat_breeds.common.breed_list.breed_list_ui.mvi.store

import com.arkivanov.mvikotlin.core.store.Executor
import com.cat_breeds.common.breed_list.breed_list_ui.mvi.BreedListIntent
import com.cat_breeds.common.breed_list.breed_list_ui.mvi.BreedListState

internal interface BreedListIntentExecutor : Executor<
        BreedListIntent,
        Unit,
        BreedListState,
        BreedListMessage,
        Nothing,
        >

