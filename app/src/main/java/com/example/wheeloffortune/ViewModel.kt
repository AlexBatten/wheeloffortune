package com.example.wheeloffortune

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class ViewModel {

    var life: MutableState<Int> = mutableStateOf(data.player.life)

    var balance: MutableState<Int> = mutableStateOf(data.player.balance)

    var wheelclick: MutableState<Boolean> = mutableStateOf(false)

    var keyboardvisibility: MutableState<Float> = mutableStateOf(1f)



}