package com.lefebvre.thomas.newordomanager.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ordonnances")

data class Ordonnance
    (
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "firstName") var firstName: String,
    @ColumnInfo(name = "dateStartLong") var dateStartLong: Long,
    @ColumnInfo(name = "dateEndLong") var dateEndLong: Long

)