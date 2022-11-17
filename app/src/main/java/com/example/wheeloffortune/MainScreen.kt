package com.example.wheeloffortune

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wheeloffortune.PlayScreen


@Composable
fun MainScreen() {
        Column(modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            WelcomePrompt()
            PressButtonPrompt()
            StartGameButton()
        }
    }

@Composable
fun WelcomePrompt() {
        Text(text = "Welcome to the Wheel of fortune! ", fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.width(400.dp))
}

@Composable
fun PressButtonPrompt() {
    Text(text = "Press the button to play", fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.width(400.dp))
}

@Composable
fun StartGameButton() {
    val context = LocalContext.current
    Button(onClick = {context.startActivity(Intent(context, PlayScreen::class.java))}, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red), shape = RoundedCornerShape(100.dp), modifier = Modifier
        .height(60.dp)
        .width(350.dp)) {
        Text(text = "PLAY GAME", Modifier.padding(0.dp,0.dp,10.dp,0.dp), color = Color.White )
    }
}
