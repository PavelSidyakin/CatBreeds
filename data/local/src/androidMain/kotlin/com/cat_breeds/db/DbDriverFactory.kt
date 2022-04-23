package com.cat_breeds.db

import android.content.Context
import com.cat_breeds.data.db.CatBreedsDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DbDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = CatBreedsDatabase.Schema,
            context = context,
            name = "CatBreedsDatabase.db"
        )
    }
}
