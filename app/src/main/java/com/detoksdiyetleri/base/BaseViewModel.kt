package com.detoksdiyetleri.base

import android.arch.lifecycle.*
import android.databinding.Observable
import android.databinding.PropertyChangeRegistry
import com.detoksdiyetleri.DetoksDiyetleriApplication
import com.detoksdiyetleri.ui.MainViewModel
import com.detoksdiyetleri.ui.detail.DetailViewModel
import com.detoksdiyetleri.ui.favorites.FavoritesItemViewModel
import com.detoksdiyetleri.ui.favorites.FavoritesViewModel
import com.detoksdiyetleri.ui.home.HomeItemViewModel
import com.detoksdiyetleri.ui.home.HomeViewModel
import io.reactivex.disposables.CompositeDisposable


abstract class
BaseViewModel: ViewModel(), Observable, LifecycleObserver {

    var swipeLoadingStatus = MutableLiveData<Boolean>()

    var loadingStatus = MutableLiveData<Boolean>()

    val errorString = MutableLiveData<String>()

    private val callbacks: PropertyChangeRegistry = PropertyChangeRegistry()

    protected val compositeDisposable = CompositeDisposable()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is MainViewModel ->DetoksDiyetleriApplication.appComponent.inject(this)
            is HomeViewModel ->DetoksDiyetleriApplication.appComponent.inject(this)
            is HomeItemViewModel ->DetoksDiyetleriApplication.appComponent.inject(this)
            is FavoritesViewModel ->DetoksDiyetleriApplication.appComponent.inject(this)
            is FavoritesItemViewModel ->DetoksDiyetleriApplication.appComponent.inject(this)
            is DetailViewModel ->DetoksDiyetleriApplication.appComponent.inject(this)


        }
    }


    open fun onRefresh(){

    }

    fun changeLoadingStatus(status:Boolean){
        loadingStatus.value = status
        loadingStatus.value = loadingStatus.value
    }


    override fun addOnPropertyChangedCallback(
            callback: Observable.OnPropertyChangedCallback) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(
            callback: Observable.OnPropertyChangedCallback) {
        callbacks.remove(callback)
    }


    fun notifyChange() {
        callbacks.notifyCallbacks(this, 0, null)
    }


    fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unSubscribeViewModel() {
        compositeDisposable.dispose()
    }

}