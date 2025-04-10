package com.example.internandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.internandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var TAG = "Lifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "MainActivity - onCreate()")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sampleText.text = stringFromJNI()

        setButtons()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "MainActivity - onStart()")
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
        binding.btnSecondActivity.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("Message", "Hello! From Main!")
            }
            startActivity(intent)
        }
    }

    external fun stringFromJNI(): String

    companion object {
        init {
            System.loadLibrary("internandroid")
        }
    }
}