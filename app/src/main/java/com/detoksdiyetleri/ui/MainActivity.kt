package com.detoksdiyetleri.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import com.detoksdiyetleri.R
import com.detoksdiyetleri.base.BaseActivity
import com.detoksdiyetleri.base.BaseFragmentPagerAdapter
import com.detoksdiyetleri.databinding.ActivityMainBinding
import com.detoksdiyetleri.ui.favorites.FavoritesFragment
import com.detoksdiyetleri.ui.home.HomeFragment


class MainActivity : BaseActivity<MainViewModel,ActivityMainBinding>() {
    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewPager()
        binding.bottomNavigation.setOnNavigationItemSelectedListener(object: BottomNavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId){
                    R.id.menu_home -> {
                        binding.viewpager.setCurrentItem(0, false)
                    }
                    R.id.menu_favorite -> {
                        binding.viewpager.setCurrentItem(1, false)
                    }
                }

                return true
            }
        })


    }


    private fun setupViewPager() {
        val adapter = BaseFragmentPagerAdapter(supportFragmentManager)
        adapter.addFrag(HomeFragment(),"")
        adapter.addFrag(FavoritesFragment(),"")

        binding.viewpager.adapter = adapter
    }
}
