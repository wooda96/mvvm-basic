package com.wooda.mvvmbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.wooda.mvvmbasic.databinding.ActivityMainBinding
import com.wooda.mvvmbasic.utils.Logger

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater).also {
            val model: MainViewModel by viewModels()
            it.vm = model
            it.lifecycleOwner = this
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Logger.d("${this.javaClass.simpleName} - onCreate()")
    }
}