package com.zayar.mycomposeapp.unscramble

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.zayar.mycomposeapp.unscramble.ui.GameScreen
import com.zayar.mycomposeapp.unscramble.ui.theme.UnscrambleTheme

class UnscrambleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnscrambleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    GameScreen()
                }
            }
        }
    }
}