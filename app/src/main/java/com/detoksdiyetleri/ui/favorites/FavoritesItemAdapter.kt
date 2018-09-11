package com.detoksdiyetleri.ui.favorites

import android.support.v7.app.AppCompatActivity
import com.detoksdiyetleri.R
import com.detoksdiyetleri.base.BaseRecyclerAdapter
import com.detoksdiyetleri.databinding.FragmentFavoritesItemBinding
import com.detoksdiyetleri.model.Diet
import com.detoksdiyetleri.ui.detail.DetailActivity
import com.detoksdiyetleri.utils.showPopUpMenu
import com.detoksdiyetleri.utils.startNewActivity
import java.io.Serializable

class FavoritesItemAdapter(activity:AppCompatActivity) : BaseRecyclerAdapter<Diet,FavoritesItemViewModel, FragmentFavoritesItemBinding>(activity){
    override fun onItemBinding(binding:FragmentFavoritesItemBinding, viewModel: FavoritesItemViewModel, position: Int) {
        binding.rippleLayout.setOnClickListener{
            val bundle = HashMap<String, Serializable>()
            bundle.put("diet",viewModel.model)
            activity.startNewActivity(DetailActivity::class.java,bundle)
        }


        binding.more.setOnClickListener {
            view->
            activity.showPopUpMenu(view,R.menu.menu_favorite_item) { id->
                when(id){
                    R.id.menu_remove_favorite->{
                        viewModel.delete()
                    }
                }
            }
        }
    }

    override fun getViewModel(): FavoritesItemViewModel {
        return FavoritesItemViewModel()
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_favorites_item
    }
}