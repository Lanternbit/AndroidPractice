package com.example.internandroid

import android.app.Application
import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics

class AppClass : Application() {
    private var TAG = "Lifecycle"

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Applcation - onCreate()")
    }
}