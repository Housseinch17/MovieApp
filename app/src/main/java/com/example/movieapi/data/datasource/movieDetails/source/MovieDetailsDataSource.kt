package com.example.movieapi.data.datasource.movieDetails.source

import com.example.movieapi.data.model.Result
import kotlinx.coroutines.flow.Flow

interface MovieDetailsDataSource {
    suspend fun getMovieDetails(movieId: Int,apiKey: String): Flow<Result?>
}