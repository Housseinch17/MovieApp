package com.example.movieapi.ui.screens.movieDetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapi.data.model.Result
import com.example.movieapi.data.uiState.MovieDetailsUiState
import com.example.movieapi.domain.usecase.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {
    private val _movieDetailsUiState = MutableStateFlow(MovieDetailsUiState())
    val movieDetailsUiState: StateFlow<MovieDetailsUiState> = _movieDetailsUiState.asStateFlow()


    init {
        Log.d("MyTag","Details Started")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("MyTag","Details Cleared")
    }

    fun showDialog(){
        _movieDetailsUiState.update {
            it.copy(showDialog = true)
        }
    }

    fun hideDialog(){
        _movieDetailsUiState.update {
            it.copy(showDialog = false)
        }
    }

    fun getMovieDetailsById(movieId: Int, apiKey: String) {
        viewModelScope.launch {
            val movieDetails: Result? = getMovieDetailsUseCase.execute(movieId, apiKey)
            val movieDetailsResponse: MovieDetailsResponse = if (movieDetails != null)
                MovieDetailsResponse.Success(movieDetails)
            else {
                MovieDetailsResponse.Error
            }
            _movieDetailsUiState.update {
                it.copy(
                    movieDetailsResponse = movieDetailsResponse
                )
            }
        }
    }
}

sealed interface MovieDetailsResponse {
    data class Success(val movieDetails: Result?) : MovieDetailsResponse
    data object Loading : MovieDetailsResponse
    data object Error : MovieDetailsResponse
}