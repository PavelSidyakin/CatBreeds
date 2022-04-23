package com.cat_breeds.common.breed_info.breed_info_ui.mvi.store

import com.arkivanov.mvikotlin.core.store.Store
import com.cat_breeds.common.breed_info.breed_info_ui.mvi.BreedInfoIntent
import com.cat_breeds.common.breed_info.breed_info_ui.mvi.BreedInfoState

internal interface BreedInfoStore: Store<BreedInfoIntent, BreedInfoState, BreedInfoLabel>