package com.example.myapplication.ui

import android.annotation.SuppressLint
import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

enum class SimpleAppScreen() {
    MAIN,
    SCREEN1,
    SCREEN1B,
    SCREEN2
}

@Composable
fun MainScreen(
    onScreen1ButtonClicked: () -> Unit,
    onScreen2ButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val intent = Intent(Intent.ACTION_SEND).apply{
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, "Main1Screen")
        putExtra(Intent.EXTRA_TEXT, "Send Main Screen")
    }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("This is the Main Screen") },
                navigationIcon = {
                    IconButton(onClick = {
                        context.startActivity(
                            Intent.createChooser(
                                intent,
                                "SimpleApp.kt"
                            )
                        )
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = "back button"
                        )
                    }
                }
            )
        }
    ) {contentPadding ->

        Column {
            Text("This is the Main Screen")
            Button(onClick = onScreen1ButtonClicked) {
                Text("Screen 1")
            }
            Button(onClick = onScreen2ButtonClicked) {
                Text("Screen 2")
            }
        }
        Modifier.padding(contentPadding) }
}



@Composable
fun Screen1(
    onScreen1bButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, "Main1Screen")
        putExtra(Intent.EXTRA_TEXT, "Send First Screen")
    }
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("This is the First Screen") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "back button"
                        )
                    }
                }
            )
        }
    ) {contentPadding ->
        Column {
            Text("This is Screen 1")
            Button(onClick = onScreen1bButtonClicked) {
                Text("Screen 1b")
            }
            Button(onClick = {
                context.startActivity(
                    Intent.createChooser(
                        intent,
                        "SimpleApp.kt"
                    )
                )
            }) {
                Text("Share")
            }
        }
        Modifier.padding(contentPadding) }
}


@Composable
fun Screen1b(
    onFinalizeButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Text("This is Screen 1")
        Button(onClick = onFinalizeButtonClicked) {
            Text("Finalize")
        }
        Button(onClick = onCancelButtonClicked) {
            Text("Cancel")
        }

    }
}

@Composable
fun Screen2(
) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, "Main1Screen")
        putExtra(Intent.EXTRA_TEXT, "Send Second Screen")
    }
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("This is the Second Screen") },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "back button"
                        )
                    }
                }
            )
        }
    ) {
            contentPadding ->
        Column {
            Text("This is Screen 2")
            Button(onClick = {
                context.startActivity(
                    Intent.createChooser(
                        intent,
                        "SimpleApp.kt"
                    )
                )
            }) {
                Text("Share")
            }
        }
        Modifier.padding(contentPadding)}

}
@Composable
fun SimpleApp(
    modifier: Modifier = Modifier,
    simpleAppViewModel: SimpleAppViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by simpleAppViewModel.uiState.collectAsState()

    val intent = Intent(Intent.ACTION_SEND).apply{
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, "Main1Screen")
        putExtra(Intent.EXTRA_SUBJECT, "Class")
    }
    val context = LocalContext.current


    NavHost(
        navController = navController,
        startDestination = SimpleAppScreen.MAIN.name
    ) {
        composable(route = SimpleAppScreen.MAIN.name) {
            MainScreen(
                onScreen1ButtonClicked = {
                    Log.d("SimpleApp.kt", "On Screen 2 Buytton Clicked")
                    navController.navigate(SimpleAppScreen.SCREEN1.name)
                },
                onScreen2ButtonClicked = {
                    Log.d("SimpleApp.kt", "On Screen 2 Button Clicked")
                    navController.navigate(SimpleAppScreen.SCREEN2.name)
                },
            )
        }
        composable(route = SimpleAppScreen.SCREEN1.name) {
            Screen1(onScreen1bButtonClicked = { /*TODO*/ })
        }
        composable(route = SimpleAppScreen.SCREEN2.name) {
            Screen2()
        }
    }
}

