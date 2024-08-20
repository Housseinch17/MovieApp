package com.example.movieapi.ui.di

import com.example.movieapi.data.api.ApiService
import com.example.movieapi.data.datasource.movieDetails.impl.MovieDetailsDataSourceImpl
import com.example.movieapi.data.datasource.movieDetails.source.MovieDetailsDataSource
import com.example.movieapi.data.datasource.popularMovies.source.PopularMoviesDataSource
import com.example.movieapi.data.datasource.popularMovies.impl.PopularMoviesDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceDi {

    @Provides
    @Singleton
    fun providePopularMoviesRemoteDataSource(apiService: ApiService): PopularMoviesDataSource {
        return PopularMoviesDataSourceImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideMovieDetailsRemoteDataSource(apiService: ApiService): MovieDetailsDataSource{
        return MovieDetailsDataSourceImpl(apiService)
    }

}