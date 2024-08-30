package com.example.movieapi.domain.repository

import com.example.movieapi.data.model.Result
import kotlinx.coroutines.flow.Flow

interface PopularMoviesRepository {
    suspend fun getPopularMovies(apiKey: String): Flow<List<Result>>
}