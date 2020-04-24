package com.lefebvre.thomas.newordomanager.database

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Ordonnance::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)

abstract class DatabaseOrdonnance : RoomDatabase() {

    abstract val ordonnanceDao: OrdonnanceDao

    companion object {

        @Volatile
        private var INSTANCE: DatabaseOrdonnance? = null

        fun getInstance(context: Context): DatabaseOrdonnance =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                DatabaseOrdonnance::class.java,
                "property_database"
            )
                .fallbackToDestructiveMigration()
                .build()


    }
}