package com.detoksdiyetleri.injection.module

import android.content.Context
import com.detoksdiyetleri.data.room.RoomDietDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

  @Provides @Singleton fun provideRoomDietDataSource(context: Context) = RoomDietDataSource.buildPersistentDiet(context)

}
