package com.cat_breeds.widgets.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import java.net.URL

@Composable
actual fun <T> AsyncImage(
    load: suspend () -> T,
    painterFor: @Composable (T) -> Painter,
    modifier: Modifier,
    contentScale: ContentScale,
) {
    throw NotImplementedError("Do not use this in Android. Use GlideImage or something like this.")
}

actual fun loadImageBitmap(url: String): ImageBitmap {
    throw NotImplementedError("Do not use this in Android. Use GlideImage or something like this.")
}
