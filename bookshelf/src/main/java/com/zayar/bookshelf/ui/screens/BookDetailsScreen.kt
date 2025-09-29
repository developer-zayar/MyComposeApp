package com.zayar.bookshelf.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
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
    volumeId: String,
    viewModel: BookViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {

    LaunchedEffect(volumeId) {
        viewModel.getBookDetails(volumeId)
    }

    val bookDetailsUiState by viewModel.bookDetailsUiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(stringResource(R.string.book_details))
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary, // Background
                    titleContentColor = MaterialTheme.colorScheme.onPrimary, // Title color
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        when (bookDetailsUiState) {
            is BookDetailsUiState.loading -> LoadingScreen(modifier = modifier.padding(innerPadding))
            is BookDetailsUiState.Success -> BookDetailsView(
                book = (bookDetailsUiState as BookDetailsUiState.Success).book,
                modifier = modifier.padding(innerPadding)
            )

            is BookDetailsUiState.Error -> ErrorScreen(
                retryAction = {
                    viewModel.getBookDetails(volumeId)
                },
                modifier.padding(innerPadding)
            )
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

    val volume = book.volumeInfo

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Book Cover
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            // Book cover
            Card(
                modifier = Modifier
                    .width(160.dp) // fixed width
                    .aspectRatio(0.7f), // typical book cover ratio
                shape = RoundedCornerShape(4.dp),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                AsyncImage(
                    model = volume?.imageLinks?.thumbnail?.replace("http", "https"),
                    contentDescription = volume?.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(Modifier.width(12.dp))

            // Title & Author
            Column(
                modifier = Modifier
                    .weight(1f) // take remaining space
//                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = volume?.title ?: "Unknown Title",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
//                    maxLines = 2,
//                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(6.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Author",
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = volume?.authors?.joinToString(", ") ?: "Unknown Author",
                        style = MaterialTheme.typography.bodyMedium,
//                        maxLines = 1,
//                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // Info Row (Publisher, Published Date, Page Count)
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                InfoRow(Icons.Default.Apartment, "Publisher", volume?.publisher ?: "Unknown")
                InfoRow(Icons.Default.CalendarMonth, "Published", volume?.publishedDate ?: "N/A")
                InfoRow(Icons.Default.MenuBook, "Pages", volume?.pageCount?.toString() ?: "N/A")
                InfoRow(Icons.Default.Language, "Language", volume?.language ?: "N/A")
            }
        }

        Spacer(Modifier.height(16.dp))

        // Categories
        volume?.categories?.takeIf { it.isNotEmpty() }?.let { categories ->
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                categories.forEach { category ->
                    AssistChip(
                        onClick = { /* could filter by category */ },
                        label = { Text(category ?: "") },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            labelColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        // Description
        Text(
            text = "Description",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = volume?.description ?: "No description available.",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun InfoRow(icon: ImageVector, label: String, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = "$label: $value",
            style = MaterialTheme.typography.bodyMedium
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