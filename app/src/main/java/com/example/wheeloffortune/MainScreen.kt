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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


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
        Text(text = stringResource(R.string.welcome_message), fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.width(400.dp), color = Color.White)
}

@Composable
fun PressButtonPrompt() {
    Text(text = stringResource(R.string.play_message), fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.width(400.dp), color = Color.White)
}

@Composable
fun StartGameButton() {
    val context = LocalContext.current
    Button(onClick = {context.startActivity(Intent(context, PlayScreen::class.java))}, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green), shape = RoundedCornerShape(100.dp), modifier = Modifier
        .height(60.dp)
        .width(350.dp)) {
        Text(text = stringResource(R.string.play_button_text), Modifier.padding(0.dp,0.dp,10.dp,0.dp), color = Color.Black )
    }
}
