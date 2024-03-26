package com.vidovicsystems.timetracker.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vidovicsystems.timetracker.data.Chronos
import kotlinx.coroutines.flow.Flow

// Interface -> Repository -> Viewmodel -> View

@Dao
interface ChronosDatabaseDao {

    // Interface -> Repository -> Viewmodel -> View

    //CRUD
    @Query("SELECT * FROM chronos")
    fun getAllChronos(): Flow<List<Chronos>>

    @Query("SELECT * FROM chronos WHERE chrono = :title")
    fun getChronoByTitle(title: String): Flow<Chronos>

    @Query("SELECT * FROM chronos WHERE id = :id")
    fun getChronoById(id: Long): Flow<Chronos>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChrono(chrono: Chronos)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateChrono(chrono: Chronos)

    @Delete
    suspend fun deleteChrono(chrono: Chronos)
}