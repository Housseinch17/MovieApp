package com.example.movieapi.data.uiState

import com.example.movieapi.ui.screens.popularMovies.ApiResponse

data class PopularMoviesUiState(
    val apiResponse: ApiResponse = ApiResponse.Loading
)
