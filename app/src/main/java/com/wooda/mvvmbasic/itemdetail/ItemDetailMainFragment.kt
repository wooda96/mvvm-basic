package com.wooda.mvvmbasic.itemdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wooda.mvvmbasic.R
import com.wooda.mvvmbasic.databinding.ItemDetailFragmentBinding
import com.wooda.mvvmbasic.model.MainItemDetail
import com.wooda.mvvmbasic.utils.BaseFragment
import com.wooda.mvvmbasic.utils.Logger

class ItemDetailMainFragment: BaseFragment() {
    companion object {
        private const val ArgId = "id"

        fun create(id: String) = ItemDetailMainFragment().also {
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
        val itemId = id ?: throw Exception("Initial item id is null")

        val binding = ItemDetailFragmentBinding.inflate(inflater, container, false).also {
            val vm: ItemDetailMainViewModel by viewModels() {
                ItemDetailMainViewModelFactory(itemId)
            }
            it.vm = vm
            it.lifecycleOwner = this
        }

        if (savedInstanceState != null) {
            savedInstanceState.keySet().forEach {
                Logger.d("bundle found - $it")
            }
        }

//        binding.pager.adapter = ItemDetailPagerAdapter(this, itemId)
        val adapter = DetailPageAdapter()
        binding.vm?.detailPagedList?.observe(viewLifecycleOwner) {
            Logger.d("New detail item is ready.")
            adapter.submitList(it)
        }
        binding.pager.adapter = adapter

        return binding.root
    }
}


private val DIFF_CALLBACK: DiffUtil.ItemCallback<MainItemDetail> = object: DiffUtil.ItemCallback<MainItemDetail>() {
    override fun areItemsTheSame(oldItem: MainItemDetail, newItem: MainItemDetail): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MainItemDetail, newItem: MainItemDetail): Boolean {
        return oldItem.equals(newItem)
    }
}

class DetailViewHolder(
    private val rootView: View
): RecyclerView.ViewHolder(rootView) {
    private val idText = rootView.findViewById<TextView>(R.id.id_text)
    private val messageText = rootView.findViewById<TextView>(R.id.message_text)

    fun bind(item: MainItemDetail) {
        idText.text = item.id
        messageText.text = item.message
    }
}


class DetailPageAdapter(): PagedListAdapter<MainItemDetail, DetailViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.item_detail_layout,
            parent,
            false
        )

        return DetailViewHolder(v)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null)
            holder.bind(item)
        else
            Logger.d("Detail item is null!! - $position")
    }
}