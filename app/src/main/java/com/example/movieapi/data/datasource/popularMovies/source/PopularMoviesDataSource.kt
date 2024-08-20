package com.example.movieapi.data.datasource.popularMovies.source

import com.example.movieapi.data.model.Result

interface PopularMoviesDataSource {
    suspend fun getPopularMovies(apiKey:String): List<Result>
}