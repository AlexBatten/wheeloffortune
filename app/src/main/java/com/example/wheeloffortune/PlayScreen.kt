package com.example.wheeloffortune

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wheeloffortune.data.Data
import com.example.wheeloffortune.ui.theme.WheeloffortuneTheme
import kotlin.random.Random

var data = Data()

class PlayScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WheeloffortuneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    newGame()
                    Combined()
                }
            }
        }
    }
}

@Composable
fun Combined() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){

        LifeField()
        WordField()
        CategoryField()
        SpinButton()
        KeyboardField()

    }
}

@Composable
fun CategoryField() {
    Text(text = stringResource(R.string.current_category) + " " + data.currentcategory, fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.width(400.dp))
}

@Composable
fun WordField() {

    Text(text = data.currentword, fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.width(400.dp))

}

//TODO: FIX STATE NOT UPDATING WHEN UPDATING LIFE AMOUNT IN DATA

@SuppressLint("UnrememberedMutableState")
@Composable
fun LifeField() {

    val lifeui by mutableStateOf(data.player.life)
    Text(text = stringResource(R.string.lifeamount) + " " + lifeui, fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.width(400.dp))
}

@Composable
fun KeyboardField() {

}

@Composable
fun SpinButton() {
    Button(onClick = {data.currentfield = randomint(data.wheel.fieldarray.size)
                     data.player.life = data.player.life-1
                     }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green), shape = RoundedCornerShape(100.dp), modifier = Modifier
        .height(60.dp)
        .width(350.dp)) {
        Text(text = stringResource(R.string.spin_button_text), Modifier.padding(0.dp,0.dp,10.dp,0.dp), color = Color.Black )
    }
}

@Preview(showBackground = true)
@Composable
fun PlayScreenPreview() {
    WheeloffortuneTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            newGame()
            Combined()
        }
    }
}

fun randomint(size: Int): Int {
    val random = Random.nextInt(size)
    return random
}

fun newGame() {

    data.currentcategory = data.categoryarray[randomint(data.categoryarray.size)]

    when (data.currentcategory) {
        "Animal" -> data.currentword = data.animalarray[randomint(data.animalarray.size)]
        "City" -> data.currentword = data.cityarray[randomint(data.cityarray.size)]
        "Food" -> data.currentword = data.foodarray[randomint(data.foodarray.size)]
        "Software" -> data.currentword = data.softwarearray[randomint(data.softwarearray.size)]
    }

    data.player.balance = 0
    data.player.life = 5
    data.currentfield = 0

}