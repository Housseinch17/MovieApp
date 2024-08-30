package com.example.movieapi.data.datasource.popularMovies.source

import com.example.movieapi.data.model.Result
import kotlinx.coroutines.flow.Flow

interface PopularMoviesDataSource {
    suspend fun getPopularMovies(apiKey:String): Flow<List<Result>>
}