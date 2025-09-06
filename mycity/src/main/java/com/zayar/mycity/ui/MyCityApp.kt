package com.zayar.mycity.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.zayar.mycity.R
import com.zayar.mycity.data.LocalDataProvider

enum class MyCityAppScreen(@StringRes val title: Int) {
    Category(title = R.string.category),
    Recommendation(title = R.string.recommendation),
    PlaceDetails(title = R.string.place_details)
}

sealed class MyCityScreen(val route: String) {
    data object Category : MyCityScreen("categories")
    data object Recommendation : MyCityScreen("recommendations/{categoryId}") {
        fun createRoute(categoryId: Int) = "recommendations/$categoryId"
    }

    data object PlaceDetails : MyCityScreen("place_details/{recommendationId}") {
        fun createRoute(recommendationId: Int) = "place_details/$recommendationId"
    }
}

@Composable
fun MyCityApp(
    viewModel: CityViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
//    val currentScreen = MyCityAppScreen.valueOf(
//        backStackEntry?.destination?.route ?: MyCityAppScreen.Category.name
//    )

//    Scaffold(
//        topBar = {
//            MyCityAppBar(
//                currentScreen = currentScreen,
//                canNavigateBack = navController.previousBackStackEntry != null,
//                navigateUp = { navController.navigateUp() }
//            )
//        }
//    ) { innerPadding ->
//
//    }

    val uiState by viewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = MyCityScreen.Category.route,
    ) {
        composable(route = MyCityScreen.Category.route) {
            CategoryScreen(
                categories = viewModel.getCategories(),
                onItemClicked = {
                    navController.navigate(MyCityScreen.Recommendation.createRoute(it.id))
                }
            )
        }

        composable(
            route = MyCityScreen.Recommendation.route,
            arguments = listOf(navArgument("categoryId") { defaultValue = 0 })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getInt("categoryId") ?: 0
            RecommendationScreen(
                recommendations = viewModel.getRecommendationsByCategory(categoryId),
                onItemClicked = {
                    navController.navigate(MyCityScreen.PlaceDetails.createRoute(it.id))
                },
                onBack = { navController.navigateUp() }
            )
        }

        composable(
            route = MyCityScreen.PlaceDetails.route,
            arguments = listOf(navArgument("recommendationId") { type = NavType.IntType })
        ) { backStackEntry ->
            val recommendationId = backStackEntry.arguments?.getInt("recommendationId") ?: 0
            PlaceDetailsScreen(
                placeDetails = viewModel.getPlaceDetails(recommendationId),
                onBack = { navController.navigateUp() }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityAppBar(
    currentScreen: MyCityAppScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}