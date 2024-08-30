package com.example.movieapi.data.uiState

import androidx.compose.runtime.Immutable
import com.example.movieapi.ui.screens.popularMovies.ApiResponse

data class PopularMoviesUiState(
    val apiResponse: ApiResponse = ApiResponse.Loading
)
