package com.cat_breeds.common

import com.cat_breeds.common.breed_info.breed_info_data.di.breedInfoDataModule
import com.cat_breeds.common.breed_info.breed_info_domain.di.breedInfoDomainModule
import com.cat_breeds.common.breed_list.breed_list_data.di.breedListDataModule
import com.cat_breeds.common.breed_list.breed_list_domain.di.breedListDomainModule
import com.cat_breeds.common.breed_list.breed_list_ui.di.breedListUiModule
import com.cat_breeds.common.di.catBreedsRootModule
import com.cat_breeds.remote.di.remoteDataModule
import org.kodein.di.DI

class CommonApp {

    fun buildCommonDI(diBuilder: DI.Builder) {
        diBuilder.run {
            importOnce(breedInfoDataModule)
            importOnce(breedInfoDomainModule)
            importOnce(breedListDataModule)
            importOnce(breedListDomainModule)
            importOnce(breedListUiModule)
            importOnce(breedListUiModule)
            importOnce(catBreedsRootModule)
            importOnce(remoteDataModule)
        }
    }
}
