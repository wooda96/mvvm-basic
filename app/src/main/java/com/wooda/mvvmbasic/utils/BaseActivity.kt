package com.wooda.mvvmbasic.utils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.lifecycle(this, "onCreate(), savedInstanceState: $savedInstanceState")
    }

    override fun onStart() {
        super.onStart()
        Logger.lifecycle(this, "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Logger.lifecycle(this, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Logger.lifecycle(this, "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Logger.lifecycle(this, "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.lifecycle(this, "onDestroy()")
    }
}