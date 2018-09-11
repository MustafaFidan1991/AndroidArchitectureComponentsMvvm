package com.detoksdiyetleri.injection.module

import com.detoksdiyetleri.data.remote.RemoteDietService
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.Reusable


@Module
@Suppress("unused")
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideFirebaseDb(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideDietService(firebaseDatabase: FirebaseDatabase): RemoteDietService {
        return RemoteDietService(firebaseDatabase)
    }



    @Provides
    @Reusable
    @JvmStatic
    internal fun provideGson(): Gson {
        return Gson()
    }


}