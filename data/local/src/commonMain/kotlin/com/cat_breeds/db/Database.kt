package com.cat_breeds.db

import com.cat_breeds.data.db.CatBreedsDatabase

internal fun createDatabase(driverFactory: DbDriverFactory): CatBreedsDatabase {
    val driver = driverFactory.createDriver()
    return CatBreedsDatabase(driver)
}
