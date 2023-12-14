package `is`.ulstu.myapplication.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import `is`.ulstu.myapplication.base.cache.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TestCacheModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDatabase =
        Room.inMemoryDatabaseBuilder(application, AppDatabase::class.java)
            .build()
}
