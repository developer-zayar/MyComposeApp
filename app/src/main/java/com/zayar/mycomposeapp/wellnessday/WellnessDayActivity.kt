package com.zayar.mycomposeapp.wellnessday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.zayar.mycomposeapp.R
import com.zayar.mycomposeapp.data.WellnessDayRepository
import com.zayar.mycomposeapp.models.WellnessDay
import com.zayar.mycomposeapp.ui.theme.MyComposeAppTheme
import com.zayar.mycomposeapp.ui.theme.WellnessDayTheme

class WellnessDayActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WellnessDayTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WellnessDayApp()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun WellnessDayApp() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.wellness_day_app_name)) }
                )
            }
        ) { innerPadding ->
            WellnessDayList(WellnessDayRepository.loadWellnessDays(), Modifier.padding(innerPadding))
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview6() {
        WellnessDayTheme {
            WellnessDayApp()
        }
    }
}

