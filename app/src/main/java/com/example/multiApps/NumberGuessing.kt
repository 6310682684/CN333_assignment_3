package com.example.multiApps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.NavController

@Composable
fun NumberGuessingScreen(navController: NavController, modifier: Modifier = Modifier) {
    var numberGen = (1..100).random()
    var number by remember { mutableStateOf(numberGen) }
    var count by remember { mutableStateOf("0") }
    var check by remember { mutableStateOf("") }
    var input by remember { mutableStateOf("") }
    var latestInput by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("true") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(36.dp))
        Text(
            text = stringResource(R.string.AS1Title),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(16.dp))
        InputField(
            label = R.string.input,
            value = input,
            onValueChange = { input = it },
            modifier = Modifier.width(300.dp)
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.result, check)
        )
        Text(
            text = stringResource(R.string.count, count)
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Button(
                onClick = { check = checkFunc(number.toInt(), input.toIntOrNull() ?:-1);
                    count = count(count, input.toIntOrNull() ?:-1, latestInput.toIntOrNull() ?:-2);
                    latestInput = input;
                    state = state(check) },
                enabled = state.toBoolean()
            ) {
                Text(stringResource(R.string.check))
            }
            Spacer(Modifier.width(32.dp))
            Button(
                onClick = { number = (1..100).random();
                    check = "" ;
                    count = "0";
                    state = "true"}
            ) {
                Text(stringResource(R.string.reset))
            }
        }
        Spacer(Modifier.height(36.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Go Back")
        }
    }
}

fun count(count: String, input: Int, latestInput: Int): String{
    return (if (input >= 0 && input != latestInput) {
        (count.toInt() + 1).toString()
    }
    else {
        count
    })
}

fun state(check: String): String {
    return if (check == "Congratulations! You are correct!") {
        "false"
    }
    else {
        "true"
    }
}

fun checkFunc(number: Int, input: Int): String {
    return (if (input < 0) {
        "Please input valid number"
    }
    else if (input > number) {
        "Your number is too high"
    }
    else if (input < number) {
        "Your number is too low"
    }
    else {
        "Congratulations! You are correct!"
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    @StringRes label: Int,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier) {
    TextField(
        value = value,
        singleLine = true,
        modifier = modifier.fillMaxWidth(),
        onValueChange = onValueChange,
        label = { Text(stringResource(label)) }
    )
}