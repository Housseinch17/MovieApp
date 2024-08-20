package com.example.movieapi.data.datasource.movieDetails.source

import com.example.movieapi.data.model.Result

interface MovieDetailsDataSource {
    suspend fun getMovieDetails(movieId: Int,apiKey: String): Result?
}