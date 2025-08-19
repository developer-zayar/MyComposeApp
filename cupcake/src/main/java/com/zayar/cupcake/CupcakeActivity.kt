package com.zayar.cupcake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.zayar.cupcake.ui.theme.CupcakeTheme

class CupcakeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CupcakeTheme {
                CupcakeApp()
            }
        }
    }
}