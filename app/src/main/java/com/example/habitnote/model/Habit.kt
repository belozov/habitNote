package com.example.habitnote.model

import androidx.compose.ui.graphics.Color

data class Habit(
    val id : Int,
    val title : String,
    val subtitle : String,
    val color : Color
)
