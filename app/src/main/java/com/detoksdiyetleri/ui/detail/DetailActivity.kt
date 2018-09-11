package com.detoksdiyetleri.ui.detail

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.MenuItem
import com.detoksdiyetleri.R
import com.detoksdiyetleri.base.BaseActivity
import com.detoksdiyetleri.databinding.ActivityDetailBinding
import com.detoksdiyetleri.model.Diet

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


        viewModel.favoriteStatus?.observe(this, Observer {
            isSuccess->
            if(isSuccess!!){
                binding.fab.setImageResource(R.drawable.ic_favorites)
                binding.fab.imageTintList = ContextCompat.getColorStateList(this, R.color.colorAccent)
            }
            else{
                binding.fab.setImageResource(R.drawable.ic_favorites)
                binding.fab.imageTintList = ContextCompat.getColorStateList(this, R.color.grey_60)
            }
        })


        binding.fab.setOnClickListener { view ->
            viewModel.addToFavorite()
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() === android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}
