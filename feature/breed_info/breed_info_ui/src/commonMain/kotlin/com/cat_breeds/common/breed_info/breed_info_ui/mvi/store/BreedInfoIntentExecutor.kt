package com.cat_breeds.common.breed_info.breed_info_ui.mvi.store

import com.arkivanov.mvikotlin.core.store.Executor
import com.cat_breeds.common.breed_info.breed_info_ui.mvi.BreedInfoIntent
import com.cat_breeds.common.breed_info.breed_info_ui.mvi.BreedInfoLabel
import com.cat_breeds.common.breed_info.breed_info_ui.mvi.BreedInfoState

internal interface BreedInfoIntentExecutor : Executor<
        BreedInfoIntent,
        Unit,
        BreedInfoState,
        BreedInfoMessage,
        BreedInfoLabel,
        >

