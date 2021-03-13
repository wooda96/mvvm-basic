package com.wooda.mvvmbasic.itemlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wooda.mvvmbasic.R
import com.wooda.mvvmbasic.databinding.ItemlistFragmentBinding
import com.wooda.mvvmbasic.model.MainListItem
import com.wooda.mvvmbasic.utils.BaseFragment
import com.wooda.mvvmbasic.utils.ItemSelectedNotifiable
import com.wooda.mvvmbasic.utils.Logger

class ItemListFragment: BaseFragment() {

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = ItemlistFragmentBinding.inflate(inflater, container, false).also {
            val vm: ItemListViewModel by viewModels()
            it.vm = vm
            it.lifecycleOwner = this
        }

        binding.itemListRecycler.also {
            it.layoutManager = LinearLayoutManager(context)
            it.setHasFixedSize(false)
        }

        val adapter = ItemListPageAdapter(activity as? ItemSelectedNotifiable)
        binding.vm?.itemPagedList?.observe(viewLifecycleOwner) {
            Logger.d("new item page list is ready.")
            binding.swipeRefreshLayout.isRefreshing = false
            adapter.submitList(it)
        }
        binding.itemListRecycler.adapter = adapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            Logger.d("Refreshing is started.")
            binding.vm?.refresh()
        }

        return binding.root
    }
}

class ItemViewHolder(
    private val rootView: View,
    private val onItemSelected: ItemSelectedNotifiable?
): RecyclerView.ViewHolder(rootView) {
    private val titleText = rootView.findViewById<TextView>(R.id.item_title)
    private val registerTimeText = rootView.findViewById<TextView>(R.id.item_register_time)

    fun bind(item: MainListItem) {
        titleText.text = item.title
        registerTimeText.text = item.registerTime.toString()

        onItemSelected?.also { notifiable ->
            rootView.setOnClickListener {
                notifiable.onItemSelected(item.id)
            }
        }
    }
}


private val DIFF_CALLBACK: DiffUtil.ItemCallback<MainListItem> = object: DiffUtil.ItemCallback<MainListItem>() {
    override fun areItemsTheSame(oldItem: MainListItem, newItem: MainListItem): Boolean {
        Logger.d("[DIFF_CALLBACK] areItemsTheSame - old: ${oldItem.id}, new: ${newItem.id}")
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MainListItem, newItem: MainListItem): Boolean {
        Logger.d("[DIFF_CALLBACK] areContentsTheSame - old: ${oldItem.id}, new ${newItem.id}")
        return oldItem.equals(newItem)
    }
}


class ItemListPageAdapter(
        private val onItemSelected: ItemSelectedNotifiable?
): PagedListAdapter<MainListItem, ItemViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout,
                parent,
                false
        )
        return ItemViewHolder(v, onItemSelected)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        } else {
            Logger.d("Item is null!!! - $position")
        }
    }
}