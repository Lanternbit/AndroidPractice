package com.example.internandroid

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.internandroid.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import com.google.firebase.crashlytics.setCustomKeys

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var TAG = "Lifecycle"
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        Log.d(TAG, "MainActivity - onCreate()")


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pageText.text = stringFromJNI()

        setButtons()
        setEditText()

        val crashButton = Button(this) //test
        crashButton.text = "Test Crash"
        crashButton.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }

        addContentView(crashButton, ViewGroup.LayoutParams( //test
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT))

        val crashlytics = Firebase.crashlytics
        crashlytics.sendUnsentReports()
        crashlytics.setCustomKeys { //custom keys
            key("my_string_key", "foo") // String value
            key("my_bool_key", true) // boolean value
            key("my_double_key", 1.0) // double value
            key("my_float_key", 1.0f) // float value
            key("my_int_key", 1) // int value
        }

        Firebase.crashlytics.log("message") //custom log
        Firebase.crashlytics.setUserId("user123456789") //user identifier
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "MainActivity - onStart()")

        setTextResult()
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "MainActivity - onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "MainActivity - onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "MainActivity - onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "MainActivity - onDestroy()")
    }

    private fun setButtons() {
        val intent1 = Intent(this, SecondActivity::class.java)
        val intent2 = Intent(this, ThirdActivity::class.java)
        binding.btnSecondActivity.setOnClickListener {
            var It = binding.inputText.text.toString()
            intent1.putExtra("InputText", It)
            binding.inputText.setText(It)
            resultLauncher.launch(intent1)
        }
        binding.btnThirdActivity.setOnClickListener {
            var It = binding.inputText.text.toString()
            intent2.putExtra("InputText", It)
            binding.inputText.setText(It)
            resultLauncher.launch(intent2)
        }
    }

    private fun setEditText() {
        binding.btnInput.setOnClickListener {
            binding.inputText.text = binding.editInput.text.toString()
            binding.editInput.text = null
        }
    }

    private fun setTextResult() {
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            result ->
            if (result.resultCode == Activity.RESULT_OK) {
                binding.inputText.text = result.data?.getStringExtra("InputText")
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