package com.cat_breeds.remote.di

import com.cat_breeds.db.createDatabase
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val localDataModule = DI.Module("localDataModule") {
    bind { singleton { createDatabase(instance()) } }
}
