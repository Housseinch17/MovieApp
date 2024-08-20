package com.example.movieapi.domain.usecase

import com.example.movieapi.data.model.Result
import com.example.movieapi.domain.repository.PopularMoviesRepository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val popularMoviesRepository: PopularMoviesRepository
) {
    suspend fun execute(apiKey: String): List<Result> =
        popularMoviesRepository.getPopularMovies(apiKey)
}