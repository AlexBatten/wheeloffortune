package com.example.wheeloffortune

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wheeloffortune.data.Data
import com.example.wheeloffortune.model.Word
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
                    color = Color.DarkGray
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
        BalanceField()

        Row(modifier = Modifier.padding(0.dp,0.dp,0.dp,20.dp)){
            CategoryField()
        }
        Row(modifier = Modifier.padding(0.dp,0.dp,0.dp,50.dp)) {
            WordField()
        }
        SpinButton()

        Row(modifier = Modifier.padding(0.dp,70.dp,0.dp,0.dp)) {
            KeyboardField()
        }

    }
}

@Composable
fun CategoryField() {
    Text(text = stringResource(R.string.current_category) + " " + data.currentcategory, fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.width(400.dp), color = Color.White)
}

//TODO: HIDE CHAR IF NOT GUESSED

@Composable
fun WordField() {

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
        for (i in data.wordarray.chararray) {
            WordButton(i, Color.DarkGray)
        }
    }

}
@Composable
fun WordButton(char: Char, characterColor: Color){

    var charactercolor by remember {mutableStateOf(Color.DarkGray)}
    charactercolor = characterColor

    Button(onClick = {/*donothing*/}, colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray), shape = RoundedCornerShape(0.dp), border = BorderStroke(1.dp, Color.White), enabled = true,  modifier = Modifier
        .height(36.dp)
        .width(36.dp)) {
        Text(text = char.toString(), Modifier.padding(0.dp,0.dp,0.dp,0.dp), color = charactercolor, fontSize = 10.sp)
    }

    fun changecolor() {
        charactercolor = Color.White
    }

}

//TODO: FIX STATE NOT UPDATING WHEN UPDATING LIFE AMOUNT IN DATA

@Composable
fun LifeField() {

    var lifeui by remember {mutableStateOf(data.player.life)}
    Text(text = stringResource(R.string.lifeamount) + " " + "$lifeui", fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.width(400.dp), color = Color.White)

    fun decrementlife() {
        data.player.life = data.player.life-1
        lifeui = lifeui - 1
    }

}

//TODO: FIX STATE NOT UPDATING WHEN UPDATING BALANCE AMOUNT IN DATA

@Composable
fun BalanceField() {

    var balanceui by remember {mutableStateOf(data.player.balance)}
    Text(text = stringResource(R.string.balanceamount) + " " + "$balanceui", fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.width(400.dp), color = Color.White)

}

@Composable
fun KeyboardField() {

    Column() {
        Row{
            KeyboardButton('Q')
            KeyboardButton('W')
            KeyboardButton('E')
            KeyboardButton('R')
            KeyboardButton('T')
            KeyboardButton('Y')
            KeyboardButton('U')
            KeyboardButton('I')
            KeyboardButton('O')
            KeyboardButton('P')
            KeyboardButton('Å')
        }
        Row{
            KeyboardButton('A')
            KeyboardButton('S')
            KeyboardButton('D')
            KeyboardButton('F')
            KeyboardButton('G')
            KeyboardButton('H')
            KeyboardButton('J')
            KeyboardButton('K')
            KeyboardButton('L')
            KeyboardButton('Æ')
            KeyboardButton('Ø')
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            KeyboardButton('Z')
            KeyboardButton('X')
            KeyboardButton('C')
            KeyboardButton('V')
            KeyboardButton('B')
            KeyboardButton('N')
            KeyboardButton('M')
        }
    }
}

@Composable
fun KeyboardButton(char: Char){

    //TODO: figure out how to update color of text in wordbuttons from the onlclick.

    Button(onClick = {
        if (data.wordarray.chararray.contains(char)){

        }
    }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray), shape = RoundedCornerShape(0.dp), border = BorderStroke(1.dp, Color.Black), modifier = Modifier
        .height(80.dp)
        .width(36.dp)) {
        Text(text = char.toString(), Modifier.padding(0.dp,0.dp,0.dp,0.dp), color = Color.White, fontSize = 14.sp)
    }

}

@Composable
fun SpinButton() {
    var click by remember {mutableStateOf(false)}
    var fieldvalue by remember { mutableStateOf(data.wheel.fieldarray[data.currentfield].point.toString())}

    Button(onClick = {
        data.currentfield = randomint(data.wheel.fieldarray.size)
        if (data.wheel.fieldarray[data.currentfield].bankrupt){
            data.player.balance = 0
        }

        click = false

        fieldvalue = data.wheel.fieldarray[data.currentfield].point.toString()
                     }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green), shape = RoundedCornerShape(100.dp), enabled = click, modifier = Modifier
        .height(60.dp)
        .width(350.dp)) {
        Text(text = stringResource(R.string.spin_button_text), Modifier.padding(0.dp,0.dp,10.dp,0.dp), color = Color.Black )
    }

    Text(text = "Wheel Field Value: " + fieldvalue, fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.width(400.dp), color = data.wheel.fieldarray[data.currentfield].color, style = TextStyle(background = Color.DarkGray))

}

@Preview(showBackground = true)
@Composable
fun PlayScreenPreview() {
    WheeloffortuneTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.DarkGray
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
    data.currentfield = randomint(data.wheel.fieldarray.size)
    data.wordarray = Word(data.currentword.toCharArray(),BooleanArray(data.currentword.length))

}