package com.wooda.mvvmbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.commit
import com.wooda.mvvmbasic.databinding.ActivityMainBinding
import com.wooda.mvvmbasic.itemdetail.ItemDetailFragment
import com.wooda.mvvmbasic.itemlist.ItemListFragment
import com.wooda.mvvmbasic.utils.BaseActivity
import com.wooda.mvvmbasic.utils.ItemSelectedNotifiable
import com.wooda.mvvmbasic.utils.Logger

class MainActivity : ItemSelectedNotifiable, BaseActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater).also {
            val vm: MainViewModel by viewModels()
            it.vm = vm
            it.lifecycleOwner = this
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            Logger.d("${this.javaClass.simpleName} - creating fragment.")
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            val itemListFragment = ItemListFragment()
            fragmentTransaction.add(R.id.fragment_container, itemListFragment)
            fragmentTransaction.commit()
        }
    }

    override fun onItemSelected(id: String) {
        Logger.d("Item selected: $id")
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, ItemDetailFragment.create(id))
            .addToBackStack("detail_$id")
            .commit()
    }
}