package com.detoksdiyetleri.ui.home

import android.arch.lifecycle.Observer
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.detoksdiyetleri.R
import com.detoksdiyetleri.base.BaseRecyclerAdapter
import com.detoksdiyetleri.databinding.FragmentHomeItemBinding
import com.detoksdiyetleri.model.Diet
import com.detoksdiyetleri.ui.detail.DetailActivity
import com.detoksdiyetleri.utils.startNewActivity
import java.io.Serializable

class HomeItemAdapter(activity:AppCompatActivity) : BaseRecyclerAdapter<Diet, HomeItemViewModel, FragmentHomeItemBinding>(activity) {
    override fun onItemBinding(binding: FragmentHomeItemBinding, viewModel: HomeItemViewModel, position: Int) {
        binding.favoriteBtn.setOnClickListener{
            viewModel.addToFavorite()
        }

        binding.cardView.setOnClickListener{
            val bundle = HashMap<String, Serializable>()
            bundle.put("diet",viewModel.model)
            activity.startNewActivity(DetailActivity::class.java,bundle)
        }

        viewModel.favoriteStatus?.observe(activity, Observer {
            isSuccess->
            if(isSuccess!!){
                binding.favoriteBtn.setImageResource(R.drawable.ic_favorites)
                binding.favoriteBtn.imageTintList = ContextCompat.getColorStateList(activity, R.color.colorAccent)
            }
            else{
                binding.favoriteBtn.setImageResource(R.drawable.ic_favorites)
                binding.favoriteBtn.imageTintList = ContextCompat.getColorStateList(activity, R.color.grey_60)
            }
        })
    }

    override fun getViewModel(): HomeItemViewModel {
        return HomeItemViewModel()
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_home_item
    }

}