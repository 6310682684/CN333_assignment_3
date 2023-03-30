package com.example.multiApps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun RPSScreen(navController: NavController, modifier: Modifier = Modifier) {
    var opponentResult by remember{ mutableStateOf(0) }
    var matchResult by remember { mutableStateOf("") }
    val imageResource = when(opponentResult) {
        0 -> R.drawable.all_rps
        1 -> R.drawable.rock
        2 -> R.drawable.paper
        else -> R.drawable.scissors

    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(36.dp))
        Text(
            text = stringResource(R.string.AS3Title),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(32.dp))
        Text(
            text = stringResource(R.string.opponent),
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(12.dp))

        Image(painter = painterResource(imageResource), contentDescription = opponentResult.toString())

        Spacer(Modifier.height(10.dp))

        Text(
            text = matchResult ,
            fontSize = 32.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(12.dp))
        Button(
            onClick = { opponentResult = (1..3).random()
                if (opponentResult == 0) {
                    matchResult = " "
                }
                else if (opponentResult == 1) {
                    matchResult = "Draw"
                }
                else if (opponentResult == 2) {
                    matchResult = "Lose"
                }
                else {
                    matchResult = "Win"
                }
            }) {
            Text(text = stringResource(R.string.choice_rock), fontSize = 24.sp)
        }
        Spacer(Modifier.height(12.dp))
        Button(
            onClick = { opponentResult = (1..3).random()
                if (opponentResult == 0) {
                    matchResult = " "
                }
                else if (opponentResult == 1) {
                    matchResult = "Win"
                }
                else if (opponentResult == 2) {
                    matchResult = "Draw"
                }
                else {
                    matchResult = "Lose"
                }
            }
        ) {
            Text(text = stringResource(R.string.choice_paper), fontSize = 24.sp)
        }
        Spacer(Modifier.height(12.dp))
        Button(
            onClick = { opponentResult = (1..3).random()
                if (opponentResult == 0) {
                    matchResult = " "
                }
                else if (opponentResult == 1) {
                    matchResult = "Lose"
                }
                else if (opponentResult == 2) {
                    matchResult = "Win"
                }
                else {
                    matchResult = "Draw"
                }
            }) {
            Text(text = stringResource(R.string.choice_scissors), fontSize = 24.sp)
        }
        Spacer(Modifier.height(36.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Go Back")
        }

    }



}