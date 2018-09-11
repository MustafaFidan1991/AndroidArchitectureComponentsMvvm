package com.detoksdiyetleri

import android.app.Application
import com.detoksdiyetleri.injection.component.DaggerViewModelInjector
import com.detoksdiyetleri.injection.component.ViewModelInjector
import com.detoksdiyetleri.injection.module.ContextModule
import com.detoksdiyetleri.injection.module.NetworkModule
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump

class DetoksDiyetleriApplication : Application() {

    companion object {
        lateinit var appComponent: ViewModelInjector
    }


    override fun onCreate() {
        super.onCreate()



        ViewPump.init(ViewPump.builder()
                .addInterceptor(CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/Quicksand-Regular.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());

        inject()

    }

    fun inject(){
        appComponent = DaggerViewModelInjector
                .builder()
                .contextModule(ContextModule(this))
                .networkModule(NetworkModule)
                .build()
    }





}