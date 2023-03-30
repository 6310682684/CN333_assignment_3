package com.example.multiApps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.multiApps.ui.theme.MultiAppsTheme

//sealed class Screen(val route: String) {
//    object Home: Screen("home")
//    object Greet: Screen("greet")
//}

enum class Screen {
    Home, NumberGuessing, Quiz, RPS
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiAppsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = Screen.Home.name) {
                        composable(Screen.Home.name) {
                            HomeScreen(navController = navController)
                        }
                        composable(Screen.NumberGuessing.name) {
                            NumberGuessingScreen(navController = navController)
                        }
                        composable(Screen.Quiz.name) {
                            QuizAppScreen(navController = navController)
                        }
                        composable(Screen.RPS.name) {
                            RPSScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GreetingScreen(name: String, navController: NavController, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Button(onClick = { navController.popBackStack() }) {
            Text("Go Back")
        }
    }
}

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(36.dp))
        Text(
            text = "Select Game",
            fontSize = 36.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(36.dp))
        Button(onClick = { navController.navigate("NumberGuessing") }) {
            Text(
                text = "Number Guessing Game",
                fontSize = 24.sp
            )
        }
        Spacer(Modifier.height(20.dp))
        Button(onClick = { navController.navigate("Quiz") }) {
            Text(
                text = "Quiz Game",
                fontSize = 24.sp
            )
        }
        Spacer(Modifier.height(20.dp))
        Button(onClick = { navController.navigate("RPS") }) {
            Text(
                text = "Rock Paper Scissor Game",
                fontSize = 24.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MultiAppsTheme {
        val navController = rememberNavController()
        HomeScreen(navController, modifier = Modifier)
    }
}