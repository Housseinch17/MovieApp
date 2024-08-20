package com.example.movieapi.ui.screens.popularMovies

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.movieapi.R
import com.example.movieapi.data.model.Result
import com.example.movieapi.ui.sharedComponent.ErrorScreen
import com.example.movieapi.ui.sharedComponent.LoadingScreen
import com.example.movieapi.ui.sharedComponent.MovieImage
import com.example.movieapi.ui.theme.MovieText
import com.example.movieapi.ui.theme.MovieTitle
import com.example.movieapi.ui.theme.ScreenBackground

@Composable
fun PopularMoviesScreen(apiResponse: ApiResponse, onItemClick: (String) -> Unit) {
    when (apiResponse) {
        is ApiResponse.Success -> MovieList(
            modifier = Modifier
                .fillMaxSize()
                .background(ScreenBackground)
                .padding(
                    horizontal = dimensionResource(id = R.dimen.small_padding),
                ),
            apiResponse.responseResult,
        ) { id ->
            onItemClick(id.toString())
        }

        is ApiResponse.Error -> ErrorScreen()
        is ApiResponse.Loading -> LoadingScreen(
        )
    }

}


@Composable
fun MovieList(
    modifier: Modifier, responseResult: List<Result>, onItemClick: (Int) -> Unit
) {
    VerticalDivider(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_padding)))
    LazyColumn(
        modifier = modifier, verticalArrangement = Arrangement.spacedBy(40.dp),
        contentPadding = PaddingValues(
            top = dimensionResource(id = R.dimen.small_padding),
            bottom = dimensionResource(id = R.dimen.medium_padding)
        )
    ) {
        items(responseResult) { result ->
            MovieItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                result = result
            ) {
                onItemClick(result.id)
            }
        }
    }
}

@Composable
fun MovieItem(modifier: Modifier, result: Result, onItemClick: () -> Unit) {
    Row(
        modifier = modifier
            .background(Color(0xFF90A4AE), RoundedCornerShape(8.dp)) // Background color and shape
            .padding(
                start = dimensionResource(id = R.dimen.medium_padding),
                end = dimensionResource(id = R.dimen.small_padding)
            )
            .clickable { onItemClick() },
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.small_padding)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MovieImage(
            modifier = Modifier
                .width(100.dp)
                .requiredHeight(140.dp)
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.small_padding))),
            result.posterPath
        )
        MovieTextItem(modifier = Modifier, result = result)
    }
}

@Composable
fun MovieTextItem(modifier: Modifier, result: Result) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.extraSmall_padding))
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = result.originalTitle,
            style = MovieTitle,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        MovieIconText(
            modifier = Modifier.fillMaxWidth(),
            text = "${result.voteAverage}",
            icon = R.drawable.ic_round_star_24
        )
        MovieIconText(
            modifier = Modifier.fillMaxWidth(),
            text = result.language,
            icon = R.drawable.ic_round_language_24
        )
        MovieIconText(
            modifier = Modifier.fillMaxWidth(),
            text = result.date,
            icon = R.drawable.ic_round_date_range_24
        )
    }
}

@Composable
fun MovieIconText(modifier: Modifier, text: String, icon: Int) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.extraSmall_padding)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "Star",
            modifier = Modifier.size(dimensionResource(id = R.dimen.medium_padding))
        )
        Text(
            text = text,
            style = MovieText,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth(1f),
            maxLines = 1
        )
    }
}