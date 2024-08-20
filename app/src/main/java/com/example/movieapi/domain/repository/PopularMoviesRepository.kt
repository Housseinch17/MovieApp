package com.example.movieapi.domain.repository

import com.example.movieapi.data.model.Result

interface PopularMoviesRepository {
    suspend fun getPopularMovies(apiKey: String): List<Result>
}