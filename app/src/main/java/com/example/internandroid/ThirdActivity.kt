package com.example.internandroid

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.example.internandroid.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding
    private var TAG = "Lifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "ThridActivity - onCreate()")

        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pageText.text = stringFromJNI()

        setButtons()
        setEditText()
    }

    override fun onStart() {
        super.onStart()

        setInputText()
        setComposeButton()
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "ThirdActivity - onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "ThridActivity - onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "ThridActivity - onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "ThridActivity - onDestroy()")
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
        val intent = Intent(this, ComposeActivity2::class.java)
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