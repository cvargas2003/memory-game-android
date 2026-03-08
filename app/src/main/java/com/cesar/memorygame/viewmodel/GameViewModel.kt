package com.cesar.memorygame.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.cesar.memorygame.R
import com.cesar.memorygame.model.CardModel
import kotlinx.coroutines.*
import androidx.lifecycle.viewModelScope

class GameViewModel : ViewModel() {
    private var isChecking = false
    var moves by mutableStateOf(0)
    var gameWon by mutableStateOf(false)
    var cards = mutableStateListOf<CardModel>()

    private var firstCardIndex: Int? = null

    init {
        startGame()
    }

    fun startGame() {

        val images = listOf(
            R.drawable.moto1,
            R.drawable.moto2,
            R.drawable.moto3,
            R.drawable.moto4,
            R.drawable.moto5,
            R.drawable.moto6
        )

        val deck = (images + images).shuffled()

        cards.clear()

        deck.forEach {
            cards.add(CardModel(it))
        }

        moves = 0
        gameWon = false
        firstCardIndex = null
    }

    fun flipCard(index: Int) {

        if (isChecking) return

        val card = cards[index]

        if (card.isFaceUp || card.isMatched) return

        card.isFaceUp = true

        if (firstCardIndex == null) {

            firstCardIndex = index

        } else {

            moves++

            val firstCard = cards[firstCardIndex!!]

            if (firstCard.imageRes == card.imageRes) {

                firstCard.isMatched = true
                card.isMatched = true
                if (cards.all { it.isMatched }) {
                    gameWon = true
                }

                firstCardIndex = null

            } else {

                isChecking = true

                viewModelScope.launch {

                    delay(1000)

                    firstCard.isFaceUp = false
                    card.isFaceUp = false

                    firstCardIndex = null
                    isChecking = false
                }
            }
        }
    }
}