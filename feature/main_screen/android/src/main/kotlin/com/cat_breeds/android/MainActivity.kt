package com.cat_breeds.android

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.cat_breeds.common.breed_info.breed_info_domain.BreedInfoInteractor
import kotlinx.coroutines.launch
import com.cat_breeds.common.globalDI
import org.kodein.di.instance

class MainActivity: AppCompatActivity() {

    private val breadInfoInteractor: BreedInfoInteractor by globalDI.instance<BreedInfoInteractor>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            Log.d("MainActivity", "${breadInfoInteractor.requestBreedInfo()}")
        }
    }
}