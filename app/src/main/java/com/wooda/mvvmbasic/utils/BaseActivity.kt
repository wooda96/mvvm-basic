package com.wooda.mvvmbasic.utils

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wooda.mvvmbasic.MainApplication
import com.wooda.mvvmbasic.utils.logger.ILogger
import javax.inject.Inject

abstract class BaseActivity: AppCompatActivity() {

    @Inject lateinit var logger: ILogger

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MainApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        logger.lifecycle(this, "onCreate(), savedInstanceState: $savedInstanceState")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        logger.lifecycle(this, "onNewIntent()")
    }

    override fun onStart() {
        super.onStart()
        logger.lifecycle(this, "onStart()")
    }

    override fun onResume() {
        super.onResume()
        logger.lifecycle(this, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        logger.lifecycle(this, "onPause()")
    }

    override fun onStop() {
        super.onStop()
        logger.lifecycle(this, "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        logger.lifecycle(this, "onDestroy()")
    }
}