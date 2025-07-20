package com.zayar.mycomposeapp.dessertclicker

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.zayar.mycomposeapp.R
import com.zayar.mycomposeapp.data.Datasource
import com.zayar.mycomposeapp.models.Dessert
import com.zayar.mycomposeapp.ui.theme.DessertClickerAppTheme

class DessertClickerActivity : ComponentActivity() {

    companion object {
        private const val TAG = "DessertClickerActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Called")
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

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: Called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Called")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DessertClickerApp() {

    val desserts = Datasource.dessertList

    var revenue by remember { mutableIntStateOf(0) }
    var dessertsSold by remember { mutableIntStateOf(0) }

    var currentDessertIndex by remember { mutableIntStateOf(0) }

    var currentDessertPrice by remember {
        mutableIntStateOf(desserts[currentDessertIndex].price)
    }

    val currentDessertAmount by remember {
        mutableIntStateOf(desserts[currentDessertIndex].startProductionAmount)
    }

    var currentDessertImageId by remember {
        mutableIntStateOf(desserts[currentDessertIndex].imageId)
    }

    Scaffold(
        topBar = {
            val intentContext = LocalContext.current
            val layoutDirection = LocalLayoutDirection.current
            DessertClickerAppBar(
                onShareButtonClicked = {
                    shareSoldDessertsInformation(
                        intentContext = intentContext,
                        dessertsSold = dessertsSold,
                        revenue = revenue
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = WindowInsets.safeDrawing.asPaddingValues()
                            .calculateStartPadding(layoutDirection),
                        end = WindowInsets.safeDrawing.asPaddingValues()
                            .calculateEndPadding(layoutDirection),
                    )
                    .background(MaterialTheme.colorScheme.primary)
            )
        }
    ) { contentPadding ->
        DessertClickerScreen(
            revenue = revenue,
            dessertsSold = dessertsSold,
            dessertImageId = currentDessertImageId,
            onDessertClicked = {
                // Update the revenue
                revenue += currentDessertPrice
                dessertsSold++

                // Show the next dessert
                val dessertToShow = determineDessertToShow(desserts, dessertsSold)
                currentDessertImageId = dessertToShow.imageId
                currentDessertPrice = dessertToShow.price
            },
            modifier = Modifier.padding(contentPadding)
        )
    }
}

/**
 * Determine which dessert to show.
 */
fun determineDessertToShow(
    desserts: List<Dessert>,
    dessertsSold: Int
): Dessert {
    var dessertToShow = desserts.first()
    for (dessert in desserts) {
        if (dessertsSold >= dessert.startProductionAmount) {
            dessertToShow = dessert
        } else {
            // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
            // you'll start producing more expensive desserts as determined by startProductionAmount
            // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
            // than the amount sold.
            break
        }
    }

    return dessertToShow
}

@Composable
private fun DessertClickerAppBar(
    onShareButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(R.string.app_name),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_medium)),
        )
        IconButton(
            onClick = onShareButtonClicked,
            modifier = Modifier.padding(end = dimensionResource(R.dimen.padding_medium)),
        ) {
            Icon(
                imageVector = Icons.Filled.Share,
                contentDescription = stringResource(R.string.share),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

/**
 * Share desserts sold information using ACTION_SEND intent
 */
private fun shareSoldDessertsInformation(intentContext: Context, dessertsSold: Int, revenue: Int) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(
            Intent.EXTRA_TEXT,
            intentContext.getString(R.string.share_text, dessertsSold, revenue)
        )
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)

    try {
        ContextCompat.startActivity(intentContext, shareIntent, null)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(
            intentContext,
            intentContext.getString(R.string.sharing_not_available),
            Toast.LENGTH_LONG
        ).show()
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