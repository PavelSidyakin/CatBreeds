package com.cat_breeds.common.breed_info.breed_info_ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.cat_breeds.common.breed_info.breed_info_ui.BreedInfoComponent
import com.cat_breeds.common.breed_info.breed_info_ui.mvi.BreedInfoState
import com.cat_breeds.resources.SharedRes
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

@Composable
actual fun BreedInfoScreen(component: BreedInfoComponent) {
    val model: BreedInfoState by component.models.subscribeAsState()

    Card(
        modifier = Modifier.fillMaxSize()
            .padding(all = 10.dp)
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
                    text = StringDesc.Resource(SharedRes.strings.no_info).toString(LocalContext.current),
                )
                else -> {
                    BoxWithConstraints {
                        val maxHeight = maxHeight
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(all = 10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween,
                        ) {
                            when (val imageUrl = model.breedInfo?.imageUrl) {
                                null -> Text(
                                    text = StringDesc.Resource(SharedRes.strings.no_image)
                                        .toString(LocalContext.current)
                                )
                                else -> {
                                    GlideImage(
                                        modifier = Modifier.heightIn(max = maxHeight / 2),
                                        imageModel = imageUrl,
                                        contentScale = ContentScale.FillWidth,
                                        shimmerParams = ShimmerParams(
                                            baseColor = MaterialTheme.colors.background,
                                            highlightColor = Color.DarkGray,
                                            durationMillis = 350,
                                            dropOff = 0.65f,
                                            tilt = 20f
                                        )
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
    }
}