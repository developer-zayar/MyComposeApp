package com.zayar.mycomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Ro
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.zayar.mycomposeapp.models.ArtWork
import com.zayar.mycomposeapp.models.artWorks
import com.zayar.mycomposeapp.ui.theme.MyComposeAppTheme

class ArtSpaceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyComposeAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtWorkApp(
                        modifier = Modifier.padding(innerPadding)
                    )
//                    Greeting3(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
                }
            }
        }
    }
}

@Composable
fun ArtWorkApp(modifier: Modifier = Modifier) {

    var currentIndex by remember { mutableStateOf(0) }
    val currentArtWork = artWorks[currentIndex]

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        Spacer(modifier = Modifier.height(16.dp))
        ArtWorkItem(art = currentArtWork)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Button(
                onClick = {
                    if (currentIndex > 0) {
                        currentIndex--
                    }
                },
                enabled = currentIndex > 0,
                modifier = Modifier.widthIn(min = 100.dp)
            ) {
                Text(text = stringResource(id = R.string.previous))
            }
            Button(
                onClick = {
                    if (currentIndex < artWorks.size - 1) {
                        currentIndex++
                    }
                },
                enabled = currentIndex < artWorks.size - 1,
                modifier = Modifier.widthIn(min = 100.dp)
            ) {
                Text(text = stringResource(id = R.string.next))
            }
        }
    }
}

@Composable
fun ArtWorkItem(art: ArtWork) {
    Column(
        modifier = Modifier
    ) {
        // --- Artwork Image Card ---
        Card(
            modifier = Modifier
                .widthIn(max = 400.dp),
            shape = MaterialTheme.shapes.small, // Apply rounded corners to the card itself
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            // The background color of the Card is its default surface color
            // which respects the theme.
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            )
        ) {
            Image(
                painter = painterResource(id = art.imageResourceId),
                contentDescription = art.contentDescription,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .padding(16.dp)
                // The Image is now clipped by the Card's shape
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
        Column(
            modifier = Modifier
                .widthIn(max = 400.dp)
                .background(
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(16.dp)
        ) {
            Text(
                text = art.title,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "By ${art.artist} (${art.year})",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = art.descriptionResourceId),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_7)
@Composable
fun GreetingPreview4() {
    MyComposeAppTheme {
        ArtWorkApp()
    }
}