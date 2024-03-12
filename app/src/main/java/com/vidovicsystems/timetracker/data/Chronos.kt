package com.vidovicsystems.timetracker.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Entity = Table
@Entity(tableName = "chronos")
data class Chronos(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "chrono") val chrono: Long
)