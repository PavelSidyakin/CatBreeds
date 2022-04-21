package com.cat_breeds.common.breed_list.breed_list_data.di

import com.cat_breeds.common.breed_list.breed_list_data.BreedListRemoteRepositoryImpl
import com.cat_breeds.common.breed_list.breed_list_domain.data.BreedListRemoteRepository
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val breedListDataModule = DI.Module("breedListDataModule") {
    bind<BreedListRemoteRepository> { singleton { BreedListRemoteRepositoryImpl(instance()) } }
}
