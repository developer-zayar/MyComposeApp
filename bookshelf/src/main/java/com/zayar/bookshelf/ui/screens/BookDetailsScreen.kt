package com.zayar.bookshelf.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.zayar.bookshelf.R
import com.zayar.bookshelf.model.Book
import com.zayar.bookshelf.ui.theme.BookshelfAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailsScreen(
    bookDetailsUiState: BookDetailsUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(R.string.app_name),
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
            )
        }
    ) { innerPadding ->
        when (bookDetailsUiState) {
            is BookDetailsUiState.loading -> LoadingScreen(modifier = modifier.padding(innerPadding))
            is BookDetailsUiState.Success -> BookDetailsView(
                book = bookDetailsUiState.book,
                modifier = modifier.padding(innerPadding)
            )

            is BookDetailsUiState.Error -> ErrorScreen(retryAction = retryAction, modifier.padding(innerPadding))
        }
    }
}

@Composable
fun BookDetailsView(
    book: Book?,
    modifier: Modifier = Modifier
) {
    if (book == null) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                Icons.Filled.Book,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.book_not_found),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "We couldn’t find the book you’re looking for.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                textAlign = TextAlign.Center
            )
        }
        return
    }

    Column(modifier = modifier.padding(16.dp)) {
        AsyncImage(
            model = book.volumeInfo?.imageLinks?.thumbnail,
            contentDescription = book.volumeInfo?.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = book.volumeInfo?.title ?: "Unknown Title",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = book.volumeInfo?.authors?.joinToString(", ") ?: "Unknown Author",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = book.volumeInfo?.description ?: "No description",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BookDetailsScreenPreview() {
    BookshelfAppTheme {
        BookDetailsView(
            null
        )
    }
}