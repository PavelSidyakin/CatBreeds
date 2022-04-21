package com.cat_breeds.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.arkivanov.decompose.defaultComponentContext
import com.cat_breeds.common.CatBreedsRoot
import com.cat_breeds.common.CatBreedsRootParams
import com.cat_breeds.common.globalDI
import org.kodein.di.instance

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root: CatBreedsRoot by globalDI.instance(arg = CatBreedsRootParams(defaultComponentContext()))

        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colors.background) {
                    RootContent(root)
                }
            }
        }
    }
}