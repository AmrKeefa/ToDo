package com.example.todo

import android.app.Application
import android.content.SharedPreferences
import androidx.room.Database
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}