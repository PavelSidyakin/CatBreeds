package com.cat_breeds.common.di

import com.cat_breeds.common.CatBreedsRoot
import com.cat_breeds.common.CatBreedsRootComponent
import com.cat_breeds.common.CatBreedsRootParams
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.factory

val catBreedsRootModule = DI.Module("catBreedsRootModule") {
    bind<CatBreedsRoot> {
        factory { params: CatBreedsRootParams ->
            CatBreedsRootComponent(
                params,
                factory(),
                factory()
            )
        }
    }
}
