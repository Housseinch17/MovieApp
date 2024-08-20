package com.example.movieapi.data.uiState

import com.example.movieapi.ui.screens.movieDetails.MovieDetailsResponse

data class MovieDetailsUiState(
    val movieDetailsResponse: MovieDetailsResponse = MovieDetailsResponse.Loading,
    val showDialog: Boolean = false
)
