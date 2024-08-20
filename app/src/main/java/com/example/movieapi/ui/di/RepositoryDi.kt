package com.example.movieapi.ui.di

import com.example.movieapi.data.datasource.movieDetails.MovieDetailsRepositoryImpl
import com.example.movieapi.data.datasource.movieDetails.impl.MovieDetailsDataSourceImpl
import com.example.movieapi.data.datasource.popularMovies.PopularMoviesRepositoryImpl
import com.example.movieapi.data.datasource.popularMovies.impl.PopularMoviesDataSourceImpl
import com.example.movieapi.domain.repository.MovieDetailsRepository
import com.example.movieapi.domain.repository.PopularMoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryDi {

    @Provides
    @Singleton
    fun providePopularMoviesRepository(popularMoviesDataSourceImpl: PopularMoviesDataSourceImpl): PopularMoviesRepository {
        return PopularMoviesRepositoryImpl(popularMoviesDataSourceImpl)
    }

    @Provides
    @Singleton
    fun provideMovieDetailsRepository(movieDetailsDataSourceImpl: MovieDetailsDataSourceImpl): MovieDetailsRepository {
        return MovieDetailsRepositoryImpl(movieDetailsDataSourceImpl)
    }
}