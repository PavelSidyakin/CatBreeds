package com.cat_breeds.common.breed.breed_data.di

import com.cat_breeds.common.breed.breed_data.BreedLocalRepositoryImpl
import com.cat_breeds.common.breed.breed_data.BreedRemoteRepositoryImpl
import com.cat_breeds.common.breed.breed_domain.data.BreedLocalRepository
import com.cat_breeds.common.breed.breed_domain.data.BreedRemoteRepository
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val breedDataModule = DI.Module("breedDataModule") {
    bind<BreedRemoteRepository> { singleton { BreedRemoteRepositoryImpl(instance()) } }
    bind<BreedLocalRepository> { singleton { BreedLocalRepositoryImpl(instance(), instance()) } }
}
