package com.cat_breeds.common.breed_list.breed_list_ui.di

import com.cat_breeds.common.breed_list.breed_list_ui.BreedList
import com.cat_breeds.common.breed_list.breed_list_ui.BreedListComponent
import com.cat_breeds.common.breed_list.breed_list_ui.BreedListComponentParams
import com.cat_breeds.common.breed_list.breed_list_ui.mvi.store.BreedListIntentExecutor
import com.cat_breeds.common.breed_list.breed_list_ui.mvi.store.BreedListIntentExecutorImpl
import com.cat_breeds.common.globalDI
import org.kodein.di.*


val breedListUiModule = DI.Module("breedListUiModule") {
    bind<BreedList> { factory { params: BreedListComponentParams -> BreedListComponent(params) } }
}

internal val breedListUiDI = DI {
    extend(globalDI)
    bind<BreedListIntentExecutor> { provider { BreedListIntentExecutorImpl(instance()) } }
}
