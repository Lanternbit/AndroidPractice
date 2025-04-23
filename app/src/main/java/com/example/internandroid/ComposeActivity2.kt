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
        System.loadLibrary("internandroid")
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



//    companion object {
//        init {
//            System.loadLibrary("native-lib")
//        }
//    }
}

@Composable
fun StopActivityButton2 (onBackPressedDispatcher: OnBackPressedDispatcher, It: String?) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = It.toString())
        Button(onClick = { onBackPressedDispatcher.onBackPressed() }) {
            Text(text = "Go ThirdActivity")
        }
    }
}

@Composable
fun CompareValue2(applicationContext: Context) {
    var value by remember { mutableStateOf(TextFieldValue()) }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TextField(
            value = value,
            onValueChange = { value = it },
            label = { Text(text = "Value") },
            singleLine = true,
            modifier = Modifier.width(width = 200.dp)
        )
        Button(onClick = {
            var length = printValueLength(value.text)
            Toast.makeText(applicationContext, length.toString(), Toast.LENGTH_SHORT).show();
        }) {
            Text(text = "Value Length")
        }
    }
}

external fun printValueLength(param: String): Int

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
                StopActivityButton2(onBackPressedDispatcher = OnBackPressedDispatcher(), "It")
//                CompareValue2()
            }
        }
    }
}
