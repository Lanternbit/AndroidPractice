package com.example.internandroid

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class ComposeActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        var It = intent.getStringExtra("InputText")

        setContent {
            Surface (
                modifier = Modifier.fillMaxSize()
            ){
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ){
                    StopActivityButton2 (onBackPressedDispatcher, It)
                    CompareValue2(applicationContext)
                }
            }
        }
    }

    external fun stringFromJNI(): String

    companion object {
        init {
            System.loadLibrary("internandroid")
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

@Composable
fun CompareValue2(applicationContext: Context) {
    var value1 by remember { mutableStateOf(TextFieldValue()) }
    var value2 by remember { mutableStateOf(TextFieldValue()) }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            TextField(
                value = value1,
                onValueChange = { value1 = it },
                label = { Text(text = "Value1") },
                singleLine = true,
                modifier = Modifier.width(width = 100.dp)
            )
            TextField(
                value = value2,
                onValueChange = { value2 = it },
                label = { Text(text = "Value2") },
                singleLine = true,
                modifier = Modifier.width(width = 100.dp)
            )
        }
        Button(onClick = {
            var cv = CV()
            var result = cv.compare(value1.text, value2.text)
            Toast.makeText(applicationContext, result.toString() + "", Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "Compare Values")
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun DefalutPreview2() {
    MaterialTheme {
        Surface (
            modifier = Modifier.fillMaxSize()
        ){
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ){
                StopActivityButton(onBackPressedDispatcher = OnBackPressedDispatcher(), "It")
//                CompareValue()
            }
        }
    }
}