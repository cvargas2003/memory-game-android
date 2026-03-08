package com.cesar.memorygame.uii

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cesar.memorygame.model.CardModel
import com.cesar.memorygame.viewmodel.GameViewModel
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton

@Composable
fun MemoryGameScreen(
    viewModel: GameViewModel,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "🏍 Memory Moto Game",
            fontSize = 34.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Movimientos: ${viewModel.moves}",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3), // mejor distribución
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            itemsIndexed(viewModel.cards) { index, card ->

                CardItem(
                    card = card,
                    onClick = {
                        viewModel.flipCard(index)
                    }
                )

            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Button(onClick = { viewModel.startGame() }) {

            Text("Reiniciar Juego")

        }

    }
}

@Composable
fun CardItem(
    card: CardModel,
    onClick: () -> Unit
) {

    val rotation by animateFloatAsState(
        targetValue = if (card.isFaceUp || card.isMatched) 180f else 0f,
        label = ""
    )

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 12f * density
            }
            .background(
                color = if (card.isFaceUp || card.isMatched)
                    Color(0xFF4CAF50)
                else
                    Color(0xFF1565C0),
                shape = RoundedCornerShape(18.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {

        if (card.isFaceUp || card.isMatched) {

            Image(
                painter = painterResource(id = card.imageRes),
                contentDescription = "Moto",
                modifier = Modifier.size(80.dp)
            )

        } else {

            Text(
                text = "?",
                fontSize = 32.sp,
                color = Color.White
            )

        }

    }
}