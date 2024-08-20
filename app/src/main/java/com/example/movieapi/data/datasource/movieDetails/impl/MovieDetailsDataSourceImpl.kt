package com.example.movieapi.data.datasource.movieDetails.impl

import com.example.movieapi.data.api.ApiService
import com.example.movieapi.data.datasource.movieDetails.source.MovieDetailsDataSource
import com.example.movieapi.data.model.Result
import javax.inject.Inject

class MovieDetailsDataSourceImpl @Inject constructor(
    private val apiService: ApiService
): MovieDetailsDataSource {
    override suspend fun getMovieDetails(movieId: Int, apiKey: String): Result? {
        return apiService.getMovieDetails(movieId,apiKey)
    }
}