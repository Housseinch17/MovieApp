package com.example.movieapi.domain.usecase

import com.example.movieapi.data.datasource.movieDetails.MovieDetailsRepositoryImpl
import com.example.movieapi.data.model.Result
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieDetailsRepositoryImpl: MovieDetailsRepositoryImpl
) {
    suspend fun execute(movieId: Int,apiKey: String): Result? = movieDetailsRepositoryImpl.getMovieDetails(movieId,apiKey)
}