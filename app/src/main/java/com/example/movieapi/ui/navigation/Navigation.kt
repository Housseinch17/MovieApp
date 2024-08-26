package com.example.movieapi.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movieapi.R
import com.example.movieapi.data.DataSource
import com.example.movieapi.ui.screens.movieDetails.MovieDetailsScreen
import com.example.movieapi.ui.screens.movieDetails.MovieDetailsViewModel
import com.example.movieapi.ui.screens.popularMovies.PopularMoviesScreen
import com.example.movieapi.ui.screens.popularMovies.PopularMoviesViewModel


@Composable
fun Navigation(modifier: Modifier,navController: NavHostController) {

    NavHost(
        navController = navController, startDestination = MovieScreens.PopularMovies.route,
        modifier = modifier
    ) {
        composable(route = MovieScreens.PopularMovies.route) {
            val moviesViewModel: PopularMoviesViewModel = hiltViewModel()
            val moviesUiState by moviesViewModel.popularMoviesUiState.collectAsStateWithLifecycle()
            PopularMoviesScreen(apiResponse = moviesUiState.apiResponse) { id ->
                navController.navigateSingleTopTo(MovieScreens.MovieDetail.withArgs(id))
            }
        }

        composable(route = MovieScreens.MovieDetail.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            val movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel()
            val movieDetailsUiState by movieDetailsViewModel.movieDetailsUiState.collectAsStateWithLifecycle()

            //parsing the id as a string
            val movieId = entry.arguments?.getString("id") ?: "533535"
            movieDetailsViewModel.getMovieDetailsById(movieId.toInt(), DataSource.API_KEY)
            MovieDetailsScreen(movieDetailsResponse = movieDetailsUiState.movieDetailsResponse,movieDetailsUiState.showDialog,movieDetailsViewModel::hideDialog,movieDetailsViewModel::showDialog)
        }
    }
}

sealed class MovieScreens(val route: String, @StringRes val titleResource: Int) {
    data object PopularMovies : MovieScreens("Popular Movies", R.string.popular_movies)
    data object MovieDetail : MovieScreens("Movie Details", R.string.movie_detail)

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
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


