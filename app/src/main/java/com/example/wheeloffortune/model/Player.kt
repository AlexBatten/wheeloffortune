package com.example.wheeloffortune.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class Player (lifeamount: Int, balanceamount: Int) {
    var life : Int = lifeamount
    var balance: Int = balanceamount
}