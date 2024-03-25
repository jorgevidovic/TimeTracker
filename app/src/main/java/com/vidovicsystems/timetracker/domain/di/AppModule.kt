package com.vidovicsystems.timetracker.domain.di

import android.content.Context
import androidx.room.Room
import com.vidovicsystems.timetracker.data.room.ChronosDatabase
import com.vidovicsystems.timetracker.data.room.ChronosDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesChronosDao(chronoDatabase: ChronosDatabase): ChronosDatabaseDao {
        return chronoDatabase.chronosDao()
    }

    @Singleton
    @Provides
    fun providesChronosDatabase(@ApplicationContext context: Context): ChronosDatabase {
        return Room.databaseBuilder(
            context,
            ChronosDatabase::class.java,
            name = "chronos_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

}