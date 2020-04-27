package com.lefebvre.thomas.newordomanager.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface OrdonnanceDao {
    @Query("SELECT* FROM ordonnances ORDER BY dateEndLong ASC, name ASC")
    fun getOrderByDate():List<Ordonnance>

    @Query("SELECT* FROM ordonnances ORDER BY name ASC, dateEndLong ASC")
    fun getOrderByName():List<Ordonnance>

    @Query("SELECT* FROM ordonnances WHERE id= :id LIMIT 1")
    fun loadOrdoById(id:Long):Ordonnance

    @Query("SELECT* FROM ordonnances WHERE name= :nom")
    fun loadOrdoById(nom:String):Ordonnance

    @Query("SELECT * FROM ordonnances ORDER BY dateEndLong ASC")
    fun getAllOrdonnances(): LiveData<List<Ordonnance>>

    @Query("SELECT * FROM ordonnances ORDER BY name ASC")
    fun getAllOrdonnancesByName(): LiveData<List<Ordonnance>>


    @Insert
    fun insert(ordonnance:Ordonnance)


    @Update
    fun updateOrdonnance(task: Ordonnance)

    @Delete
    fun deleteOrdonnance(task:Ordonnance)

    @Delete
    fun deleteAllOrdonnance(task:List<Ordonnance>)


}