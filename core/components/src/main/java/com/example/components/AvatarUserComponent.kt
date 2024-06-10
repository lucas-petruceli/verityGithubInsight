package com.example.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage

@Composable
fun AvatarUserComponent(
    imageUrl: String?,
    width: Dp,
    height: Dp,
    shape: Shape? = null
) {
    val modifier = if (shape != null)
        Modifier
            .clip(shape)
            .width(height)
            .height(width)
    else
        Modifier
            .width(height)
            .height(width)

    SubcomposeAsyncImage(
        model = imageUrl,
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop,
        loading = { },
        error = {
            Image(
                painter = painterResource(R.drawable.bugdroid),
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .height(90.dp)
            )
        }
    )
}