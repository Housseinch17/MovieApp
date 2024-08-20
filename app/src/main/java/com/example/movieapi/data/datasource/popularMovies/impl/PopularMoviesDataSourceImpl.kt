package com.example.movieapi.data.datasource.popularMovies.impl

import com.example.movieapi.data.api.ApiService
import com.example.movieapi.data.datasource.popularMovies.source.PopularMoviesDataSource
import com.example.movieapi.data.model.Result
import javax.inject.Inject

class PopularMoviesDataSourceImpl @Inject constructor(
    private val apiService: ApiService
): PopularMoviesDataSource {
    override suspend fun getPopularMovies(apiKey: String): List<Result> {
        return apiService.getPopularMovies(apiKey).results
    }
}