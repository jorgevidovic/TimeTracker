package com.vidovicsystems.timetracker.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vidovicsystems.timetracker.data.Chronos

@Database(entities = [Chronos::class], version = 1, exportSchema = false)
abstract class ChronosDatabase : RoomDatabase() {
    abstract fun chronosDao(): ChronosDatabaseDao
}