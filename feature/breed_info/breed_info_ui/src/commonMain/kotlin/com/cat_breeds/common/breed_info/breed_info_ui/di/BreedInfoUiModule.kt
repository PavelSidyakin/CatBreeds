package com.cat_breeds.common.breed_info.breed_info_ui.di

import com.cat_breeds.common.breed_info.breed_info_ui.BreedInfoComponent
import com.cat_breeds.common.breed_info.breed_info_ui.BreedInfoComponentImpl
import com.cat_breeds.common.breed_info.breed_info_ui.BreedInfoComponentParams
import com.cat_breeds.common.breed_info.breed_info_ui.mvi.store.BreedInfoIntentExecutor
import com.cat_breeds.common.breed_info.breed_info_ui.mvi.store.BreedInfoIntentExecutorImpl
import com.cat_breeds.common.globalDI
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.factory
import org.kodein.di.instance


val breedInfoUiModule = DI.Module("breedInfoUiModule") {
    bind<BreedInfoComponent> {
        factory { params: BreedInfoComponentParams ->
            BreedInfoComponentImpl(
                params
            )
        }
    }
}

internal val breedInfoUiDI by lazy {
    DI {
        extend(globalDI)
        bind<BreedInfoIntentExecutor> {
            factory { breedId: String ->
                BreedInfoIntentExecutorImpl(
                    breedId,
                    instance()
                )
            }
        }
    }
}
