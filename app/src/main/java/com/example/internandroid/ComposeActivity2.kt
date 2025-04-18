package com.example.internandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class ComposeActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        var It = intent.getStringExtra("InputText")

        setContent {
            StopActivityButton2(onBackPressedDispatcher, It)
        }
    }
}

@Composable
fun StopActivityButton2 (onBackPressedDispatcher: OnBackPressedDispatcher, It: String?) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Text(text = It.toString())
        Button(onClick = { onBackPressedDispatcher.onBackPressed() }) {
            Text(text = "Go ThirdActivity")
        }
    }
}