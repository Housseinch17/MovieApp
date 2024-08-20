package com.example.movieapi.data.datasource.movieDetails

import android.util.Log
import com.example.movieapi.data.datasource.movieDetails.impl.MovieDetailsDataSourceImpl
import com.example.movieapi.data.model.Result
import com.example.movieapi.domain.repository.MovieDetailsRepository
import retrofit2.HttpException
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val movieDetailsDataSourceImpl: MovieDetailsDataSourceImpl
): MovieDetailsRepository {
    override suspend fun getMovieDetails(movieId: Int, apiKey: String): Result? {
        return getMovieDetailsFromApi(movieId,apiKey)
    }

    private suspend fun getMovieDetailsFromApi(movieId: Int, apiKey: String): Result? {
            var movieDetails: Result? = null
            try{
                movieDetails = movieDetailsDataSourceImpl.getMovieDetails(movieId,apiKey)
            } catch (exception: Exception) {
                Log.i("MyTag", "getMovieDetailsFromApi() exception: " + exception.message.toString())
            } catch (e: HttpException) {
                Log.i("MyTag", "getMovieDetailsFromApi() exception: " + e.message.toString())
            }
            return movieDetails
    }
}