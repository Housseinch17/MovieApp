package com.example.movieapi.data.datasource.movieDetails

import android.util.Log
import com.example.movieapi.data.datasource.movieDetails.impl.MovieDetailsDataSourceImpl
import com.example.movieapi.data.model.Result
import com.example.movieapi.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val movieDetailsDataSourceImpl: MovieDetailsDataSourceImpl,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
): MovieDetailsRepository {
    override suspend fun getMovieDetails(movieId: Int, apiKey: String): Flow<Result?> {
        return getMovieDetailsFromApi(movieId,apiKey)
    }

    private suspend fun getMovieDetailsFromApi(movieId: Int, apiKey: String): Flow<Result?> = withContext(ioDispatcher){
            var movieDetails: Flow<Result?> = emptyFlow()
            try{
                movieDetails = movieDetailsDataSourceImpl.getMovieDetails(movieId,apiKey)
            } catch (exception: Exception) {
                Log.i("MyTag", "getMovieDetailsFromApi() exception: " + exception.message.toString())
            } catch (e: HttpException) {
                Log.i("MyTag", "getMovieDetailsFromApi() exception: " + e.message.toString())
            }
            return@withContext movieDetails
    }
}