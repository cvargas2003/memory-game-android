package com.cesar.memorygame.uii

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cesar.memorygame.model.CardModel
import com.cesar.memorygame.viewmodel.GameViewModel

@Composable
fun MemoryGameScreen(
    viewModel: GameViewModel,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Memory Game",
            fontSize = 30.sp
        )

        Text(
            text = "Movimientos: ${viewModel.moves}",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .padding(16.dp)
                .height(400.dp)
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

        Button(onClick = { viewModel.startGame() }) {

            Text("Reiniciar")

        }

    }
}

@Composable
fun CardItem(
    card: CardModel,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .padding(8.dp)
            .size(70.dp)
            .background(Color.Gray)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {

        if (card.isFaceUp || card.isMatched) {

            Text(
                text = card.value.toString(),
                fontSize = 24.sp
            )

        } else {

            Text(
                text = "?",
                fontSize = 24.sp
            )

        }

    }
}