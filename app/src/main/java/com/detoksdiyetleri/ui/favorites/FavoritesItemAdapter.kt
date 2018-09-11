package com.detoksdiyetleri.ui.favorites

import android.support.v7.app.AppCompatActivity
import com.detoksdiyetleri.R
import com.detoksdiyetleri.base.BaseRecyclerAdapter
import com.detoksdiyetleri.databinding.FragmentFavoritesItemBinding
import com.detoksdiyetleri.model.Diet

class FavoritesItemAdapter(activity:AppCompatActivity) : BaseRecyclerAdapter<Diet,FavoritesItemViewModel, FragmentFavoritesItemBinding>(activity){
    override fun onItemBinding(binding:FragmentFavoritesItemBinding, viewModel: FavoritesItemViewModel, position: Int) {

    }

    override fun getViewModel(): FavoritesItemViewModel {
        return FavoritesItemViewModel()
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_favorites_item
    }
}