package com.example.movieapi.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movieapi.R
import com.example.movieapi.ui.navigation.MovieScreens
import com.example.movieapi.ui.navigation.Navigation
import com.example.movieapi.ui.navigation.navigateSingleTopTo
import com.example.movieapi.ui.theme.ScreenBackground


@Composable
fun HomeScreen() {
    val navHostController = rememberNavController()
    // Get current back stack entry
    val backStackEntry by navHostController.currentBackStackEntryAsState()

    //all this section is used for topBar to get the title for it

    // Retrieve the current route from the back stack entry, or default to the Amphibians screen if null
    val route =
        backStackEntry?.destination?.route ?: MovieScreens.PopularMovies.route

    // Determine the current screen based on the route
    val currentScreen = when {
        // Check if the route starts with the base name for the AmphibianDetail screen.
        // This handles routes with dynamic parameters like "MovieDetail/12312" cause here in MovieDetail there is an argument .
        route.startsWith(MovieScreens.MovieDetail.route) -> MovieScreens.MovieDetail

        // Check if the route exactly matches the name for the Amphibians screen.
        route == MovieScreens.PopularMovies.route -> MovieScreens.PopularMovies

        // Add more cases if there are additional screens with different static routes
        // For example:
        // route == MovieScreens.OtherScreen.route -> MovieScreens.OtherScreen

        // Default case: if none of the specific routes match, fall back to the default screen
        else -> MovieScreens.PopularMovies
    }

    Scaffold(
        topBar = {
            AppBar(
                modifier = Modifier,
                movieScreens = currentScreen,
                canNavigateBack = navHostController.previousBackStackEntry != null
            ) {
                navHostController.navigateUp()
            }
        },
        ) { innerPadding ->
        Navigation(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding), navController = navHostController
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    modifier: Modifier,
    movieScreens: MovieScreens,
    canNavigateBack: Boolean,
    navigateBack: () -> Unit,
) {

    TopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = ScreenBackground
        ),
        modifier = modifier,
        title = {
            Box(modifier = Modifier.fillMaxSize().padding(end = dimensionResource(id = R.dimen.small_padding)), contentAlignment = Alignment.Center){
            Text(stringResource(movieScreens.titleResource))
            }
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }
        },
    )
}

