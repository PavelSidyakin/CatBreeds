package com.cat_breeds.remote.di

import com.cat_breeds.cat_api.CatApi
import com.cat_breeds.cat_api.CatApiImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val remoteDataModule = DI.Module("remoteDataModule") {
    bind<CatApi> { singleton { CatApiImpl() } }
}