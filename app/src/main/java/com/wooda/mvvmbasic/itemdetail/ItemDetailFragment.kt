package com.wooda.mvvmbasic.itemdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.wooda.mvvmbasic.databinding.ItemDetailFragmentBinding
import com.wooda.mvvmbasic.utils.BaseFragment

class ItemDetailFragment: BaseFragment() {

    companion object {
        private const val ArgId = "id"

        fun create(id: String) = ItemDetailFragment().also {
            val args = Bundle()
            args.putString(ArgId, id)
            it.arguments = args
        }
    }

    private val id: String? by lazy {
        arguments?.getString(ArgId)
    }

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val itemId = id ?: throw Exception("Something wrong...")

        val binding = ItemDetailFragmentBinding.inflate(inflater, container, false).also {
            val vm: ItemDetailViewModel by viewModels {
                ItemDetailViewModelFactory(itemId)
            }
            it.vm = vm
            it.lifecycleOwner = this
        }

        return binding.root
    }
}