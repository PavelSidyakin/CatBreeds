package com.cat_breeds.common.breed_info.breed_info_ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.cat_breeds.common.breed_info.breed_info_ui.BreedInfoComponent
import com.cat_breeds.resources.SharedRes
import com.cat_breeds.widgets.compose.AsyncImage
import com.cat_breeds.widgets.compose.loadImageBitmap
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

@Composable
actual fun BreedInfoScreen(component: BreedInfoComponent) {
    val model by component.models.subscribeAsState()

    Card(
        modifier = Modifier.fillMaxSize()
            .padding(all = 10.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            when (model.isLoading) {
                true -> Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Gray),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(color = Color.Blue)
                }
                false -> when (val breedInfo = model.breedInfo) {
                    null -> Text(
                        text = StringDesc.Resource(SharedRes.strings.no_info).localized(),
                    )
                    else -> {
                        BoxWithConstraints {
                            val maxHeight = maxHeight
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .verticalScroll(rememberScrollState())
                                    .padding(all = 10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceBetween,
                            ) {
                                when (val imageUrl = model.breedInfo?.imageUrl) {
                                    null -> Text(
                                        text = StringDesc.Resource(SharedRes.strings.no_image).localized(),
                                    )
                                    else -> {
                                        AsyncImage(
                                            modifier = Modifier,
                                            contentScale = ContentScale.Fit,
                                            load = { loadImageBitmap(imageUrl) },
                                            painterFor = { remember { BitmapPainter(it) } },
                                        )
                                    }
                                }

                                Text(text = breedInfo.name)
                                Text(text = breedInfo.origin.orEmpty())
                                Text(text = breedInfo.temperament.orEmpty())
                                Text(text = breedInfo.description.orEmpty())
                            }
                        }
                    }
                }
            }
            Icon(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .clickable { component.onCloseClicked() },
                imageVector = Icons.Outlined.Close,
                contentDescription = null,
            )
        }
    }
}
