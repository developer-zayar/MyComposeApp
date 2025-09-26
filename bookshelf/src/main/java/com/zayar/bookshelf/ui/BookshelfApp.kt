package com.zayar.bookshelf.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.zayar.bookshelf.R
import com.zayar.bookshelf.ui.screens.BookDetailsScreen
import com.zayar.bookshelf.ui.screens.BookViewModel
import com.zayar.bookshelf.ui.screens.HomeScreen

sealed class BookshelfScreen(val route: String) {
    data object Search : BookshelfScreen("search")
    data object Details : BookshelfScreen("details/{volumeId}") {
        fun createRoute(volumeId: String) = "details/$volumeId"
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfApp(
    navController: NavHostController = rememberNavController()
) {
    val bookViewModel: BookViewModel = viewModel(factory = BookViewModel.Factory)
    val uiState by bookViewModel.uiState.collectAsState()
    val bookDetailsUiState by bookViewModel.bookDetailsUiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = BookshelfScreen.Search.route
    ) {
        composable(route = BookshelfScreen.Search.route) {
            HomeScreen(
                bookUiState = uiState,
                retryAction = {
                    bookViewModel.searchBooks("jazz+history")
                },
                onBookClick = { book ->
                    book.id?.let {
                        navController.navigate(BookshelfScreen.Details.createRoute(it))
                    }
                },
            )
        }

        composable(
            route = BookshelfScreen.Details.route,
            arguments = listOf(navArgument("volumeId") { defaultValue = 0 })
        ) { backStackEntry ->
            val volumeId = backStackEntry.arguments?.getString("volumeId") ?: ""
            bookViewModel.getBookDetails(volumeId)
            BookDetailsScreen(
                bookDetailsUiState = bookDetailsUiState,
                retryAction = {
                    bookViewModel.searchBooks("jazz+history")
                },
            )
        }
    }
}