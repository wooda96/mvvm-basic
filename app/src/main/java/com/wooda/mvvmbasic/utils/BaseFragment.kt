package com.wooda.mvvmbasic.utils

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wooda.mvvmbasic.MainApplication
import com.wooda.mvvmbasic.utils.logger.ILogger
import javax.inject.Inject

abstract class BaseFragment: Fragment() {

    @Inject lateinit var logger: ILogger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logger.lifecycle(this, "onCreate() - savedInstanceState: $savedInstanceState")
    }

    override fun onAttach(context: Context) {
        (activity?.applicationContext as? MainApplication)?.appComponent?.inject(this)
        super.onAttach(context)
        logger.lifecycle(this, "onAttach()")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logger.lifecycle(this, "onCreateView() - savedInstanceState: $savedInstanceState")
        return createView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        logger.lifecycle(this, "onActivityCreated() - savedInstanceState: $savedInstanceState")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        logger.lifecycle(this, "onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        logger.lifecycle(this, "onDestroy()")
    }

    override fun onDetach() {
        super.onDetach()
        logger.lifecycle(this, "onDetach()")
    }

    abstract fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
}