package com.cat_breeds.common.breed_info.breed_info_data.di

import com.cat_breeds.common.breed_info.breed_info_data.BreedInfoRemoteRepositoryImpl
import com.cat_breeds.common.breed_info.breed_info_domain.data.BreedInfoRemoteRepository
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val breedInfoDataModule = DI.Module("breedInfoDataModule") {
    bind<BreedInfoRemoteRepository> { singleton { BreedInfoRemoteRepositoryImpl(instance()) } }
}
