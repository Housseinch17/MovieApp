package com.example.movieapi.data.model.popularMovies

import com.example.movieapi.data.model.Result
import kotlinx.serialization.Serializable

@Serializable
data class ResponseResult(
    val results: List<Result>,
)
