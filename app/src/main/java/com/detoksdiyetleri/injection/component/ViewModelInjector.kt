package com.detoksdiyetleri.injection.component

import com.detoksdiyetleri.injection.module.*
import com.detoksdiyetleri.ui.MainViewModel
import com.detoksdiyetleri.ui.detail.DetailViewModel
import com.detoksdiyetleri.ui.favorites.FavoritesItemViewModel
import com.detoksdiyetleri.ui.favorites.FavoritesViewModel
import com.detoksdiyetleri.ui.home.HomeItemViewModel
import com.detoksdiyetleri.ui.home.HomeViewModel
import dagger.Component
import javax.inject.Singleton


/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class),ViewModelModule::class,RoomModule::class])
interface ViewModelInjector {

    fun inject(mainViewModel: MainViewModel)
    fun inject(homeViewModel: HomeViewModel)
    fun inject(homeItemViewModel: HomeItemViewModel)
    fun inject(favoritesItemViewModel: FavoritesItemViewModel)
    fun inject(favoritesViewModel: FavoritesViewModel)
    fun inject(detailViewModel: DetailViewModel)



    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
        fun networkModule(networkModule: NetworkModule): Builder
        fun contextModule(contextModule: ContextModule): Builder
    }
}