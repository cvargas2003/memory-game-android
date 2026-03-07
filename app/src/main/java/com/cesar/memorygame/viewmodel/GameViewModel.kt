package com.cesar.memorygame.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.cesar.memorygame.model.CardModel

class GameViewModel : ViewModel() {

    var moves by mutableStateOf(0)

    var cards = mutableStateListOf<CardModel>()

    private var firstCardIndex: Int? = null

    init {
        startGame()
    }

    fun startGame() {

        val values = listOf(1, 2, 3, 4, 5, 6)

        val deck = (values + values).shuffled()

        cards.clear()

        deck.forEach {
            cards.add(CardModel(it))
        }

        moves = 0
        firstCardIndex = null
    }

    fun flipCard(index: Int) {

        val card = cards[index]

        if (card.isFaceUp || card.isMatched) return

        card.isFaceUp = true

        if (firstCardIndex == null) {

            firstCardIndex = index

        } else {

            moves++

            val firstCard = cards[firstCardIndex!!]

            if (firstCard.value == card.value) {

                firstCard.isMatched = true
                card.isMatched = true

            } else {

                firstCard.isFaceUp = false
                card.isFaceUp = false
            }

            firstCardIndex = null
        }
    }
}