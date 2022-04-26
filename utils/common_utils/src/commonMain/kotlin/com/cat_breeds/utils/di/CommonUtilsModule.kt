package com.cat_breeds.utils.di

import com.cat_breeds.utils.DispatcherProvider
import com.cat_breeds.utils.DispatcherProviderImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val commonUtilsModule = DI.Module("commonUtilsModule") {
    bind<DispatcherProvider> { singleton { DispatcherProviderImpl() } }
}
