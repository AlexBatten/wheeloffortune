package com.example.wheeloffortune

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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

    var viewmodel = ViewModel()

    viewmodel.addbooleans()

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){

        LifeField(viewmodel)
        BalanceField(viewmodel)

        Row(modifier = Modifier.padding(0.dp,0.dp,0.dp,20.dp)){
            CategoryField()
        }
        Row(modifier = Modifier.padding(0.dp,0.dp,0.dp,50.dp)) {
            WordField(viewmodel)
        }
        SpinButton(viewmodel)

        Row(modifier = Modifier.padding(0.dp,70.dp,0.dp,0.dp)) {
            KeyboardField(viewmodel)
        }

    }
}

@Composable
fun CategoryField() {
    Text(text = stringResource(R.string.current_category) + " " + data.currentcategory, fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.width(400.dp), color = Color.White)
}

//TODO: UNHIDE CHAR IF GUESSED

@Composable
fun WordField(viewmodel: ViewModel) {

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
        for (i in data.wordarray.chararray.indices) {
            WordButton(data.wordarray.chararray[i], viewmodel, i)
        }
    }
}

@Composable
fun WordButton(char: Char, viewmodel: ViewModel, index: Int){

    var index = index
    var show = viewmodel.wordvisibility[index]

    var charactercolor by remember {mutableStateOf(Color.DarkGray)}

    Button(onClick = {/*donothing*/}, colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray), shape = RoundedCornerShape(0.dp), border = BorderStroke(1.dp, Color.White), enabled = !show,  modifier = Modifier
        .height(36.dp)
        .width(36.dp)) {
        Text(text = char.toString(), Modifier.padding(0.dp,0.dp,0.dp,0.dp), color = charactercolor, fontSize = 10.sp)
    }

}

@Composable
fun LifeField(viewmodel: ViewModel) {

    var life = viewmodel.life
    Text(text = stringResource(R.string.lifeamount) + " " + life.value, fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.width(400.dp), color = Color.White)

}

//TODO: FIX STATE NOT UPDATING WHEN UPDATING BALANCE AMOUNT IN DATA

@Composable
fun BalanceField(viewmodel: ViewModel) {


    Text(text = stringResource(R.string.balanceamount) + " " + viewmodel.balance.value, fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.width(400.dp), color = Color.White)

}

@Composable
fun KeyboardField(viewmodel: ViewModel) {

    Column(modifier = Modifier.alpha(viewmodel.keyboardvisibility.value)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            KeyboardButton('Q', viewmodel)
            KeyboardButton('W', viewmodel)
            KeyboardButton('E', viewmodel)
            KeyboardButton('R', viewmodel)
            KeyboardButton('T', viewmodel)
            KeyboardButton('Y', viewmodel)
            KeyboardButton('U', viewmodel)
            KeyboardButton('I', viewmodel)
            KeyboardButton('O', viewmodel)
            KeyboardButton('P', viewmodel)
            KeyboardButton('Å', viewmodel)
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            KeyboardButton('A', viewmodel)
            KeyboardButton('S', viewmodel)
            KeyboardButton('D', viewmodel)
            KeyboardButton('F', viewmodel)
            KeyboardButton('G', viewmodel)
            KeyboardButton('H', viewmodel)
            KeyboardButton('J', viewmodel)
            KeyboardButton('K', viewmodel)
            KeyboardButton('L', viewmodel)
            KeyboardButton('Æ', viewmodel)
            KeyboardButton('Ø', viewmodel)
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            KeyboardButton('Z', viewmodel)
            KeyboardButton('X', viewmodel)
            KeyboardButton('C', viewmodel)
            KeyboardButton('V', viewmodel)
            KeyboardButton('B', viewmodel)
            KeyboardButton('N', viewmodel)
            KeyboardButton('M', viewmodel)
        }
    }
}

@Composable
fun KeyboardButton(char: Char, viewmodel: ViewModel){
    var click by remember {mutableStateOf(true)}
    val context = LocalContext.current
    val char = char

    Button(onClick = {

        viewmodel.wheelclick.value = true
        viewmodel.keyboardvisibility.value = 0f

        if (data.wordarray.chararray.contains(char)){

            for (i in data.wordarray.chararray.indices) {
                if (data.wordarray.chararray[i] == char){
                    data.wordarray.guessed[i] = true
                    viewmodel.wordvisibility[i] = true
                    data.player.balance = data.player.balance + data.wheel.fieldarray[data.currentfield].point
                    viewmodel.balance.value = data.player.balance
                }

            }

            // WIN CONDITION

            if (!data.wordarray.guessed.contains(false)){
                context.startActivity(Intent(context, WinScreen::class.java))
            }

        } else if (data.player.life == 1){
            context.startActivity(Intent(context, LooseScreen::class.java))
        } else {
            data.player.life = data.player.life - 1
            viewmodel.life.value = viewmodel.life.value - 1
        }

        if (data.wheel.fieldarray[data.currentfield].bankrupt){
            data.player.balance = 0
            viewmodel.balance.value = data.player.balance
        }

        click = false

    }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray), shape = RoundedCornerShape(0.dp), border = BorderStroke(1.dp, Color.Black), enabled = click, modifier = Modifier
        .height(80.dp)
        .width(36.dp)) {
        Text(text = char.toString(), Modifier.padding(0.dp,0.dp,0.dp,0.dp), color = Color.White, fontSize = 14.sp)
    }

}

// Is both the spin button and the text displaying the wheel field value

@Composable
fun SpinButton(viewmodel: ViewModel) {
    var fieldvalue by remember { mutableStateOf(data.wheel.fieldarray[data.currentfield].point.toString())}

    Button(onClick = {
        data.currentfield = randomint(data.wheel.fieldarray.size)
        viewmodel.keyboardvisibility.value = 1f
        if (data.wheel.fieldarray[data.currentfield].bankrupt){
            data.player.balance = 0
        }

        viewmodel.wheelclick.value = false

        fieldvalue = data.wheel.fieldarray[data.currentfield].point.toString()
                     }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green), shape = RoundedCornerShape(100.dp), enabled = viewmodel.wheelclick.value, modifier = Modifier
        .height(60.dp)
        .width(350.dp)) {
        Text(text = stringResource(R.string.spin_button_text), Modifier.padding(0.dp,0.dp,10.dp,0.dp), color = Color.Black )
    }

    Text(text = "Wheel Field Value: " + fieldvalue, fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.width(400.dp), color = data.wheel.fieldarray[data.currentfield].color, style = TextStyle(background = Color.DarkGray))

}

@Composable
fun EndMessage() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(R.string.loss) + " " + stringResource(R.string.playagain),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(400.dp),
            color = Color.White
        )
        StartGameButton()
    }
}

@Composable
fun WinMessage() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(R.string.win) + " " + "\n" + data.player.balance + "\n" + stringResource(R.string.playagain),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(400.dp),
            color = Color.White
        )
        StartGameButton()
    }
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