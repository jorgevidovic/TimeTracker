package com.vidovicsystems.timetracker.data.repository

import com.vidovicsystems.timetracker.data.Chronos
import com.vidovicsystems.timetracker.data.room.ChronosDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ChronosRepository @Inject constructor(private val chronosDatabaseDao: ChronosDatabaseDao) {
    fun getAllChronos(): Flow<List<Chronos>> = chronosDatabaseDao.getAllChronos().flowOn(Dispatchers.IO).conflate()
    fun getChronoById(id:Long): Flow<Chronos> = chronosDatabaseDao.getChronoById(id).flowOn(Dispatchers.IO).conflate()
    suspend fun insertChronos(chronos: Chronos) = chronosDatabaseDao.insertChrono(chronos)
    suspend fun updateChronos(chronos: Chronos) = chronosDatabaseDao.updateChrono(chronos)
    suspend fun deleteChronos(chronos: Chronos) = chronosDatabaseDao.deleteChrono(chronos)

}