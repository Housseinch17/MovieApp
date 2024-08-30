package com.example.movieapi.ui.screens.popularMovies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapi.data.DataSource
import com.example.movieapi.data.model.Result
import com.example.movieapi.data.uiState.PopularMoviesUiState
import com.example.movieapi.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {
    private val _popularMoviesUiState = MutableStateFlow(PopularMoviesUiState())
    val popularMoviesUiState = _popularMoviesUiState.asStateFlow()

    init {
        Log.d("MyTag","Popular started")
        getPopularMoviesUiState(DataSource.API_KEY)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("MyTag","Popular Cleared")
    }

    private fun getPopularMoviesUiState(apiKey: String) {
        viewModelScope.launch {
            _popularMoviesUiState.update {
                val responseResult = getPopularMoviesUseCase.execute(apiKey)
                val apiResponse: ApiResponse =
                    if (responseResult != emptyFlow<List<Result>>()){
                        ApiResponse.Success(responseResult)
                    }
                    else {
                        ApiResponse.Error
                    }
                it.copy(
                    apiResponse = apiResponse
                )
            }
        }
    }
}

sealed interface ApiResponse{
    data class Success(val responseResult: Flow<List<Result>>): ApiResponse
    data object Loading: ApiResponse
    data object Error: ApiResponse
}
