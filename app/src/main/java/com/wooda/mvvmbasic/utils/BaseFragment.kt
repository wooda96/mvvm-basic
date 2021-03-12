package com.wooda.mvvmbasic.utils

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.lifecycle(this, "onCreate() - savedInstanceState: $savedInstanceState")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Logger.lifecycle(this, "onAttach()")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Logger.lifecycle(this, "onCreateView() - savedInstanceState: $savedInstanceState")
        return createView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Logger.lifecycle(this, "onActivityCreated() - savedInstanceState: $savedInstanceState")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Logger.lifecycle(this, "onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.lifecycle(this, "onDestroy()")
    }

    override fun onDetach() {
        super.onDetach()
        Logger.lifecycle(this, "onDetach()")
    }

    abstract fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
}