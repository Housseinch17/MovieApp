package com.example.movieapi.data.datasource.popularMovies

import android.util.Log
import com.example.movieapi.data.datasource.popularMovies.impl.PopularMoviesDataSourceImpl
import com.example.movieapi.data.model.Result
import com.example.movieapi.domain.repository.PopularMoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class PopularMoviesRepositoryImpl @Inject constructor(
    private val popularMoviesDataSourceImpl: PopularMoviesDataSourceImpl,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : PopularMoviesRepository {
    override suspend fun getPopularMovies(apiKey: String): Flow<List<Result>>{
        return getPopularMoviesFromApi(apiKey)
    }

    private suspend fun getPopularMoviesFromApi(apiKey: String): Flow<List<Result>> = withContext(ioDispatcher){
        var responseResult: Flow<List<Result>> = emptyFlow()
        try{
            responseResult = popularMoviesDataSourceImpl.getPopularMovies(apiKey)
        } catch (exception: Exception) {
            Log.i("MyTag", "getPopularMoviesFromApi() exception: " + exception.message.toString())
        } catch (e: HttpException) {
            Log.i("MyTag", "getPopularMoviesFromApi() exception: " + e.message.toString())
        }
        return@withContext responseResult
    }
}