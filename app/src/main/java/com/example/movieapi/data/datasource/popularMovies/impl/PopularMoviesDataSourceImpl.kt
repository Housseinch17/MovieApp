package com.example.movieapi.data.datasource.popularMovies.impl

import com.example.movieapi.data.api.ApiService
import com.example.movieapi.data.datasource.popularMovies.source.PopularMoviesDataSource
import com.example.movieapi.data.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class PopularMoviesDataSourceImpl @Inject constructor(
    private val apiService: ApiService
): PopularMoviesDataSource {
    override suspend fun getPopularMovies(apiKey: String): Flow<List<Result>> {
        return flowOf(apiService.getPopularMovies(apiKey).results)
    }
}