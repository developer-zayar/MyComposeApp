package com.zayar.mycomposeapp.dessertclicker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zayar.mycomposeapp.R
import com.zayar.mycomposeapp.data.Datasource
import com.zayar.mycomposeapp.ui.theme.DessertClickerAppTheme

class DessertClickerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DessertClickerAppTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                ) {
                    DessertClickerApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DessertClickerApp() {

    val desserts = Datasource.dessertList

    var revenue by remember { mutableIntStateOf(0) }
    var dessertsSold by remember { mutableIntStateOf(0) }

    var currentDessertIndex by remember { mutableIntStateOf(0) }

    val currentDessertPrice by remember {
        mutableIntStateOf(desserts[currentDessertIndex].price)
    }

    val currentDessertAmount by remember {
        mutableIntStateOf(desserts[currentDessertIndex].startProductionAmount)
    }

    val currentDessertImageId by remember {
        mutableIntStateOf(desserts[currentDessertIndex].imageId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Dessert Clicker")
                }
            )
        }
    ) { contentPadding ->
        DessertClickerScreen(
            revenue = revenue,
            dessertsSold = dessertsSold,
            dessertImageId = currentDessertImageId,
            onDessertClicked = {
                if (currentDessertAmount > 0) {
                    revenue += currentDessertPrice
                    dessertsSold++
                } else {
                    if (currentDessertIndex < desserts.size - 1) {
                        currentDessertIndex++
                    } else {
                        // Reset to the first dessert if all desserts are sold out
                        currentDessertIndex = 0
                    }
                }
//                revenue += currentDessertPrice
//                dessertsSold++

//                if (dessertsSold >= desserts[currentDessertIndex].startProductionAmount) {
//                    currentDessertIndex++
//                }
            },
            modifier = Modifier.padding(contentPadding)
        )
    }
}

@Composable
fun DessertClickerScreen(
    revenue: Int,
    dessertsSold: Int,
    @DrawableRes dessertImageId: Int,
    onDessertClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.bakery_back),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(dessertImageId),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(160.dp)
                        .height(160.dp)
                        .align(Alignment.Center)
                        .clickable { onDessertClicked() },
                )
            }

            TransactionInfo(
                revenue = revenue,
                dessertsSold = dessertsSold,
                modifier = Modifier.background(MaterialTheme.colorScheme.secondary),
            )

        }
    }
}

@Composable
fun TransactionInfo(
    revenue: Int,
    dessertsSold: Int,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Desserts Sold",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSecondary,
            )
            Text(
                "$dessertsSold",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSecondary,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Revenue",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSecondary,
            )
            Text(
                "$$revenue",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSecondary,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DessertClickerAppPreview() {
    DessertClickerAppTheme {
        DessertClickerApp()
    }
}