package com.cat_breeds.db

import com.cat_breeds.data.db.CatBreedsDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import java.io.File

actual class DbDriverFactory {
    actual fun createDriver(): SqlDriver {
        val databasePath = File(System.getProperty("java.io.tmpdir"), "CatBreedsDatabase.db")
        val driver = JdbcSqliteDriver(url = "jdbc:sqlite:${databasePath.absolutePath}")
        CatBreedsDatabase.Schema.create(driver)
        return driver
    }
}
