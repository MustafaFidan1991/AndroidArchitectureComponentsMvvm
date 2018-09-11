package com.detoksdiyetleri.ui.detail

import android.arch.lifecycle.Observer
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.detoksdiyetleri.R
import com.detoksdiyetleri.base.BaseActivity
import com.detoksdiyetleri.databinding.ActivityDetailBinding
import com.detoksdiyetleri.model.Diet
import com.detoksdiyetleri.utils.setDrawableWithTint
import com.detoksdiyetleri.utils.startShareActivity

class DetailActivity : BaseActivity<DetailViewModel, ActivityDetailBinding>() {
    override fun getViewModel(): Class<DetailViewModel> {
        return DetailViewModel::class.java
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_detail
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)

        binding.viewModel = viewModel

        viewModel.diet = intent.getSerializableExtra("diet") as Diet
        viewModel.setValues()

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);


        viewModel.favoriteStatus.observe(this, Observer {
            isSuccess->
            if(isSuccess!!){
                binding.fab.drawable.setTint(ContextCompat.getColor(this, R.color.grey_40))
            }
            else{
                binding.fab.drawable.setTint(ContextCompat.getColor(this, R.color.colorWhite))
            }
        })


        binding.fab.setOnClickListener {
            view ->
            viewModel.addToFavorite()
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                finish()
            }
            R.id.menu_share->{
                this.startShareActivity(viewModel.getShareContent())
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_detail, menu)
        return true
    }
}
