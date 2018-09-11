package com.detoksdiyetleri.ui.favorites

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.detoksdiyetleri.R
import com.detoksdiyetleri.base.BaseFragment
import com.detoksdiyetleri.databinding.FragmentFavoritesBinding
import com.detoksdiyetleri.utils.SpacingItemDecoration
import com.detoksdiyetleri.utils.convertDpToPixel


class FavoritesFragment : BaseFragment<FavoritesViewModel, FragmentFavoritesBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_favorites
    }

    override fun getViewModel(): Class<FavoritesViewModel> {
        return FavoritesViewModel::class.java
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = viewModel

        binding.adapter = FavoritesItemAdapter(this.activity!! as AppCompatActivity)

        binding.recyclerView.addItemDecoration(SpacingItemDecoration(2, 8.convertDpToPixel(activity as Context), true))
        binding.layoutManager = GridLayoutManager(activity,2)


        viewModel.loadFavoritesDiets()?.observe(this, Observer {
            list->
            if(list!!.size>0){
                binding.adapter!!.update(list)
                showNoItemView(false)
            }
            else{
                showNoItemView(true)
            }
        })
        return binding.root
    }


    override fun showNoItemView(status:Boolean) {

        if(status){
            binding.recyclerView.visibility = View.GONE
            binding.noItemView?.root?.visibility = View.VISIBLE
        }
        else{
            binding.recyclerView.visibility = View.VISIBLE
            binding.noItemView?.root?.visibility = View.GONE
        }


    }



}
