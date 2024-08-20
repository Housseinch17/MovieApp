package com.example.movieapi.ui.sharedComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapi.R
import com.example.movieapi.data.DataSource
import com.example.movieapi.ui.theme.ScreenBackground


@Composable
fun LoadingScreen() {
    Image(
        painter = painterResource(id = R.drawable.loading),
        contentDescription = stringResource(id = R.string.loading),
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize().background(ScreenBackground)
    )
}


@Composable
fun ErrorScreen() {
    Column(
        modifier = Modifier.fillMaxSize().background(ScreenBackground),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.connectionerror),
            contentDescription = stringResource(
                id = R.string.failed_to_load
            )
        )
        Text(
            text = stringResource(id = R.string.failed_to_load),
            modifier = Modifier,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun MovieImage(modifier: Modifier, imageSrc: String?) {
    if (imageSrc != null) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(DataSource.POSTER_BASE_URL + imageSrc)
                .crossfade(true)
                .error(R.drawable.connectionerror)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier)
    }
}