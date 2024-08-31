package com.example.movieapi.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.movieapi.R
import com.example.movieapi.data.DataSource
import com.example.movieapi.ui.screens.movieDetails.MovieDetailsScreen
import com.example.movieapi.ui.screens.movieDetails.MovieDetailsViewModel
import com.example.movieapi.ui.screens.popularMovies.PopularMoviesScreen
import com.example.movieapi.ui.screens.popularMovies.PopularMoviesViewModel
import kotlinx.serialization.Serializable


@Composable
fun Navigation(modifier: Modifier,navController: NavHostController) {

    NavHost(
        navController = navController, startDestination = MovieScreens.PopularMovies,
        modifier = modifier
    ) {
        composable<MovieScreens.PopularMovies> {
            val moviesViewModel: PopularMoviesViewModel = hiltViewModel()
            val moviesUiState by moviesViewModel.popularMoviesUiState.collectAsStateWithLifecycle()
            PopularMoviesScreen(apiResponse = moviesUiState.apiResponse) { id ->
                navController.navigate(MovieScreens.MovieDetail(
                    id = id
                ))
            }
        }

        composable<MovieScreens.MovieDetail> { entry ->
            val args = entry.toRoute<MovieScreens.MovieDetail>()
            val movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel()
            val movieDetailsUiState by movieDetailsViewModel.movieDetailsUiState.collectAsStateWithLifecycle()

            //parsing the id as a string
            val movieId = args.id
            movieDetailsViewModel.getMovieDetailsById(movieId.toInt(), DataSource.API_KEY)
            MovieDetailsScreen(movieDetailsResponse = movieDetailsUiState.movieDetailsResponse,movieDetailsUiState.showDialog,movieDetailsViewModel::hideDialog,movieDetailsViewModel::showDialog)
        }
    }
}


@Serializable
sealed class MovieScreens(val route: String, @StringRes val titleResource: Int) {
    @Serializable
    data object PopularMovies : MovieScreens("Popular Movies", R.string.popular_movies)

    @Serializable
    data class MovieDetail(val id: String) : MovieScreens("Movie Details", R.string.movie_detail)
}


fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reelecting the same item
        launchSingleTop = true
        // Restore state when reelecting a previously selected item
        restoreState = true
    }


