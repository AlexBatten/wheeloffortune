package com.example.wheeloffortune.data

import com.example.wheeloffortune.model.Player
import com.example.wheeloffortune.model.Wheel

class Data {

    var player = Player(5,0)
    var wheel = Wheel()
    var currentcategory: String = ""
    var currentword: String = ""

    fun newGame() {

        player.balance = 0
        player.life = 5

    }

    val categoryarray = arrayOf(
        "Animal",
        "City",
        "Food",
        "Software"
    )

    val animalarray = arrayOf(
        "CAPYBARA",
        "ZEBRA",
        "GIRAFFE",
        "PELICAN"
    )

    val cityarray = arrayOf(
        "COPENHAGEN",
        "BERLIN",
        "AMSTERDAM",
        "OTTAWA"
    )

    val foodarray = arrayOf(
        "RATATOUILLE",
        "BURGER",
        "RASPBERRY",
        "SPINACH"
    )

    val softwarearray = arrayOf(
        "JAVA",
        "KOTLIN",
        "PHYTHON",
        "MAPLE"
    )


}