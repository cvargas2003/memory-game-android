package com.cesar.memorygame.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class CardModel(

    val imageRes: Int

) {

    var isFaceUp by mutableStateOf(false)

    var isMatched by mutableStateOf(false)

}