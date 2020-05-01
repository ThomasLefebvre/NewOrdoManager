package com.lefebvre.thomas.newordomanager.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface OrdonnanceDao {


    @Query("SELECT* FROM ordonnances WHERE id= :id LIMIT 1")
    fun loadOrdoById(id:Long):Ordonnance

    @Query("SELECT * FROM ordonnances WHERE name LIKE :nameQuery")
    fun getOrdoByNameQuery(nameQuery:String):List<Ordonnance>


    @Query("SELECT * FROM ordonnances ORDER BY dateEndLong ASC")
    fun getAllOrdonnancesByDateEnd(): List<Ordonnance>

    @Query("SELECT * FROM ordonnances ORDER BY name COLLATE NOCASE ASC")
    fun getAllOrdonnancesByName(): List<Ordonnance>


    @Insert
    fun insert(ordonnance:Ordonnance)


    @Update
    fun updateOrdonnance(task: Ordonnance)

    @Delete
    fun deleteOrdonnance(task:Ordonnance)

    @Delete
    fun deleteAllOrdonnance(task:List<Ordonnance>)


}