package com.wooda.mvvmbasic

import android.os.Bundle
import androidx.activity.viewModels
import com.wooda.mvvmbasic.databinding.ActivityMainBinding
import com.wooda.mvvmbasic.itemdetail.ItemDetailMainFragment
import com.wooda.mvvmbasic.itemlist.ItemListFragment
import com.wooda.mvvmbasic.utils.BaseActivity
import com.wooda.mvvmbasic.utils.ItemSelectedNotifiable

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
            logger.d("${this.javaClass.simpleName} - creating fragment.")
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            val itemListFragment = ItemListFragment()
            fragmentTransaction.add(R.id.fragment_container, itemListFragment)
            fragmentTransaction.commit()
        }
    }

    override fun onItemSelected(id: String) {
        logger.d("Item selected: $id")
        // add 로 할 경우 exit, popEnter animation 은 적용 되지 않음
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
            .add(R.id.fragment_container, ItemDetailMainFragment.create(id))
            .addToBackStack("detail_$id")
            .commit()
    }
}