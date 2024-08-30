package com.example.movieapi.domain.repository

import com.example.movieapi.data.model.Result
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: Int, apiKey: String): Flow<Result?>
}