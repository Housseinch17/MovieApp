package com.example.movieapi.data.api

import com.example.movieapi.data.model.popularMovies.ResponseResult
import com.example.movieapi.data.model.Result
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key")apiKey:String
    ): ResponseResult

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id")movieId: Int,
        @Query("api_key")apiKey: String
    ): Result?
}