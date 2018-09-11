package com.detoksdiyetleri.ui.home

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.detoksdiyetleri.R
import com.detoksdiyetleri.base.BaseFragment
import com.detoksdiyetleri.databinding.FragmentHomeBinding
import com.detoksdiyetleri.utils.EndlessScrollListener


class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    lateinit var endlessScrollListener: EndlessScrollListener

    override fun getLayoutRes(): Int {
        return R.layout.fragment_home
    }

    override fun getViewModel(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding.viewModel = viewModel
        refreshLayout = binding.swipeRefreshLayout

        binding.adapter = HomeItemAdapter(this.activity!! as AppCompatActivity)
        binding.layoutManager = LinearLayoutManager(this.activity!! as AppCompatActivity)
        binding.dividerItemDecoration = DividerItemDecoration(this.activity!! as AppCompatActivity, LinearLayoutManager.VERTICAL)

        viewModel.loadDiets()?.observe(this, Observer {
            list->
            binding.adapter!!.update(list!!)
        })

        endlessScrollListener = EndlessScrollListener(object: EndlessScrollListener.RefreshList {
            override fun onRefresh() {
                viewModel.increasePageNumber()
                viewModel.loadDiets()
            }
        })

        binding.feedsRecyclerView.addOnScrollListener(endlessScrollListener)

        return binding.root
    }

}
