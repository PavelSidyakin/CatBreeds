package com.cat_breeds.common

import com.cat_breeds.common.breed.breed_data.di.breedDataModule
import com.cat_breeds.common.breed.breed_domain.BreedInteractor
import com.cat_breeds.common.breed.breed_domain.di.breedDomainModule
import com.cat_breeds.common.breed_info.breed_info_domain.di.breedInfoDomainModule
import com.cat_breeds.common.breed_info.breed_info_ui.di.breedInfoUiModule
import com.cat_breeds.common.breed_list.breed_list_domain.di.breedListDomainModule
import com.cat_breeds.common.breed_list.breed_list_ui.di.breedListUiModule
import com.cat_breeds.common.di.catBreedsRootModule
import com.cat_breeds.remote.di.localDataModule
import com.cat_breeds.remote.di.remoteDataModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.instance

class CommonApp {

    private val breedInteractor: BreedInteractor by globalDI.instance()

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    fun buildCommonDI(diBuilder: DI.Builder) {
        diBuilder.run {
            importOnce(breedInfoDomainModule)
            importOnce(breedListDomainModule)
            importOnce(breedListUiModule)
            importOnce(breedInfoUiModule)
            importOnce(catBreedsRootModule)
            importOnce(remoteDataModule)
            importOnce(localDataModule)
            importOnce(breedDomainModule)
            importOnce(breedDataModule)
        }
    }

    fun onCreate() {
        applicationScope.launch {
            breedInteractor.initBreeds()
        }
    }
}
