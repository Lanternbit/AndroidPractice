package com.example.internandroid

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.internandroid.databinding.ActivitySecondBinding
import android.util.Log

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var TAG = "Lifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "SecondActivity - onCreate()")

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pageText.text = stringFromJNI()

        setButtons()
        setEditText()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "SecondActivity - onStart()")

        setInputText()
        setComposeButton()
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "SecondActivity - onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "SecondActivity - onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "SecondActivity - onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "SecondActivity - onDestroy()")
    }

    private fun setButtons() {
        val intent = Intent(this, MainActivity::class.java)
        binding.btnMainActivity.setOnClickListener {
            var It = binding.inputText.text.toString()
            intent.putExtra("InputText", It)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun setComposeButton() {
        val intent = Intent(this, ComposeActivity1::class.java)
        binding.btnComposeActivity.setOnClickListener {
            var It = binding.inputText.text.toString()
            intent.putExtra("InputText", It)
            binding.inputText.setText(It)
            startActivity(intent)
        }
    }

    private fun setEditText() {
        binding.btnInput.setOnClickListener {
            binding.inputText.text = binding.editInput.text.toString()
            binding.editInput.text = null
        }
    }

    private fun setInputText() {
        binding.inputText.text = intent.getStringExtra("InputText")
    }

    external fun stringFromJNI(): String

    companion object {
        init {
            System.loadLibrary("internandroid")
        }
    }
}