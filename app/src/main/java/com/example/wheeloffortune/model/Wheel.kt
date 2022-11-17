package com.example.wheeloffortune.model

import androidx.compose.ui.graphics.Color

class Wheel {

    val Brown: Color = Color(0xFF7C4700)
    val Orange: Color = Color(0xFFFFA500)

    private val fieldarray = arrayOf(
        Field(0, true, Color.Black),
        Field(500, false, Color.Yellow),
        Field(600, false, Orange),
        Field(800, false, Color.Red),
        Field(1500, false, Orange),
        Field(500, false, Color.Yellow),
        Field(600, false, Orange),
        Field(1000, false, Color.Red),
        Field(100, false, Color.Green),
        Field(0, true, Color.Black),
        Field(800, false, Color.Blue),
        Field(500, false, Color.Yellow),
        Field(800, false, Orange),
        Field(800, false, Color.Red),
        Field(500, false, Color.Green),
        Field(800, false, Color.Blue),
        Field(500, false, Color.Yellow),
        Field(2500, false, Brown),
        Field(1000, false, Orange),
        Field(300, false, Color.Red),
        Field(500, false, Color.Green),
        Field(100, false, Color.Blue),
    )
}