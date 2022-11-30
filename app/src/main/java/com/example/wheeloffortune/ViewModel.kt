package com.example.wheeloffortune

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList

class ViewModel {

    var life: MutableState<Int> = mutableStateOf(data.player.life)

    var balance: MutableState<Int> = mutableStateOf(data.player.balance)

    var wheelclick: MutableState<Boolean> = mutableStateOf(false)

    var keyboardvisibility: MutableState<Float> = mutableStateOf(1f)

    var wordvisibility: MutableList<Boolean> = mutableStateListOf()

    fun addbooleans(){
        for (i in data.wordarray.guessed) {
        wordvisibility.add(i)
        }
    }

}