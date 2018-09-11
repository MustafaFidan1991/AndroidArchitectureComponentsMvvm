package com.detoksdiyetleri.injection.module

import android.app.Application
import android.content.Context
import com.detoksdiyetleri.DetoksDiyetleriApplication
import com.detoksdiyetleri.base.BaseView
import com.detoksdiyetleri.utils.ResourceProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Module which provides all required dependencies about Context
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
class ContextModule(private val application: DetoksDiyetleriApplication) {

    @Provides @Singleton
    fun provideContext(): Context {
        return application.applicationContext
    }

    @Provides @Singleton
    fun provideApplication(): Application {
        return application
    }


    @Provides @Singleton
    fun provideResourceProvider(context: Context): ResourceProvider {
        return ResourceProvider(context)
    }

}