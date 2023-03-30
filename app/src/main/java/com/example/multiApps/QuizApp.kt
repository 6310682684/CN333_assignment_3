package com.example.multiApps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.booleanResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.util.Random

@Composable
fun QuizAppScreen(navController: NavController, modifier: Modifier = Modifier) {

    var indexList by remember {
        mutableStateOf(mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9))
    }
    var randomIndex by remember {
        mutableStateOf(indexList.random())
    }
    val allQuestions = mutableListOf(
        "2 + 5 = ?",
        "3 x 2 = ?",
        "24 ÷ 4 = ?",
        "12 - 5 = ?",
        "169 ÷ 13 = ?",
        "9 + 43 = ?",
        "15 - 13 = ?",
        "16 x 7 = ?",
        "19 + 5 = ?",
        "26 - 3 = ?"
    )
    var question by remember { mutableStateOf(allQuestions[randomIndex]) }
    var correctAnswer by remember { mutableStateOf(answers(randomIndex)[0]) }
    var answer by remember { mutableStateOf(answers(randomIndex)) }
    var check by remember { mutableStateOf("none") }
    var score by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(36.dp))
        Text(
            text = stringResource(R.string.AS2Title),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(32.dp))
        if (indexList.isEmpty()) {
            Text(
                text = "Finish",
                fontSize = 24.sp)
            Spacer(Modifier.height(10.dp))
            Text(
                text = "Score = $score/10",
                fontSize = 24.sp)
            Spacer(Modifier.height(10.dp))
            Button(onClick = {
                indexList = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
                score = 0;
                check = "none"
            }) {
                Text(text = "Reset")
            }
        }
        else {
            Button(
                onClick = {
                    indexList.remove(randomIndex);
                    randomIndex = if (indexList.isEmpty()) 0 else indexList.random();
                    question = allQuestions[randomIndex];
                    correctAnswer = answers(randomIndex)[0];
                    answer = answers(randomIndex);
                    answer.shuffle()
                }) {
                Text(text = "Next Question")
            }
            Spacer(Modifier.height(32.dp))
            Text(
                text = "${11 - indexList.count()}. " + question,
                fontSize = 24.sp
            )
            Spacer(Modifier.height(16.dp))
            /*choice 1*/
            Button(onClick = {
                check = if (answer[0] == correctAnswer) "true" else "false";
                indexList.remove(randomIndex);
                randomIndex = if (indexList.isEmpty()) 0 else indexList.random();
                question = allQuestions[randomIndex];
                correctAnswer = answers(randomIndex)[0];
                answer = answers(randomIndex);
                answer.shuffle()
            }) {
                Text(text = answer[0])
            }

            /*choice 2*/
            Button(
                onClick = {
                    check = if (answer[1] == correctAnswer) "true" else "false";
                    indexList.remove(randomIndex);
                    randomIndex = if (indexList.isEmpty()) 0 else indexList.random();
                    question = allQuestions[randomIndex];
                    correctAnswer = answers(randomIndex)[0];
                    answer = answers(randomIndex);
                    answer.shuffle()
                }) {
                Text(text = answer[1])
            }

            /*choice 3*/
            Button(onClick = {
                check = if (answer[2] == correctAnswer) "true" else "false";
                indexList.remove(randomIndex);
                randomIndex = if (indexList.isEmpty()) 0 else indexList.random();
                question = allQuestions[randomIndex];
                correctAnswer = answers(randomIndex)[0];
                answer = answers(randomIndex);
                answer.shuffle()
            }) {
                Text(text = answer[2])
            }

            /*choice 4*/
            Button(onClick = {
                check = if (answer[3] == correctAnswer) "true" else "false";
                indexList.remove(randomIndex);
                randomIndex = if (indexList.isEmpty()) 0 else indexList.random();
                question = allQuestions[randomIndex];
                correctAnswer = answers(randomIndex)[0];
                answer = answers(randomIndex);
                answer.shuffle()
            }) {
                Text(text = answer[3])
            }

            if (check == "true") {
                score += 1
            }

            check = "none"
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Score = $score",
                fontSize = 24.sp
            )
        }
        Spacer(Modifier.height(36.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Go Back")
        }
    }
}

fun answers(index: Int): MutableList<String> {
    val answerList = mutableListOf("7", "6", "6", "7", "13", "52", "2", "112", "24", "23")
    return mutableListOf(
        answerList[index],
        (80..120).random().toString(),
        (40..80).random().toString(),
        (0..40).random().toString()
    )
}

