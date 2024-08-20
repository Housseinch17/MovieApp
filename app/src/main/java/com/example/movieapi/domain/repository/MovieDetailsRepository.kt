package com.example.movieapi.domain.repository

import com.example.movieapi.data.model.Result

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: Int, apiKey: String): Result?
}