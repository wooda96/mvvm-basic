package com.wooda.mvvmbasic.itemlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wooda.mvvmbasic.R
import com.wooda.mvvmbasic.databinding.ItemlistFragmentBinding
import com.wooda.mvvmbasic.model.MainListItem
import com.wooda.mvvmbasic.utils.BaseFragment
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

        val itemsObserver = Observer<List<MainListItem>> {
            binding.itemListRecycler.layoutManager = LinearLayoutManager(context)
            binding.itemListRecycler.adapter = ItemListAdapter(it)
        }

        binding.vm?.items?.observe(viewLifecycleOwner, itemsObserver)

        return binding.root
    }
}

class ItemViewHolder(rootView: View): RecyclerView.ViewHolder(rootView) {
    private val titleText = rootView.findViewById<TextView>(R.id.item_title)
    private val registerTimeText = rootView.findViewById<TextView>(R.id.item_register_time)

    fun bind(item: MainListItem) {
        titleText.text = item.title
        registerTimeText.text = item.registerTime.toString()
    }
}

class ItemListAdapter(private val itemList: List<MainListItem>): RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.item_layout,
            parent,
            false
        )
        return ItemViewHolder(v)
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

}