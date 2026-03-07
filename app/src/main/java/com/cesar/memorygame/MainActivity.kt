package com.cesar.memorygame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cesar.memorygame.ui.theme.MemoryGameTheme
import com.cesar.memorygame.uii.MemoryGameScreen
import com.cesar.memorygame.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            MemoryGameTheme {

                val viewModel: GameViewModel = viewModel()

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    MemoryGameScreen(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )

                }
            }
        }
    }
}