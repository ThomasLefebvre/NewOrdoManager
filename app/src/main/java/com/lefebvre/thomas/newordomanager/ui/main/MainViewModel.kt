package com.lefebvre.thomas.newordomanager.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Database
import com.lefebvre.thomas.newordomanager.database.DatabaseOrdonnance
import com.lefebvre.thomas.newordomanager.database.Ordonnance
import com.lefebvre.thomas.newordomanager.database.OrdonnanceDao
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList

class MainViewModel(application: Application) :
    AndroidViewModel(application) {
    //coroutine
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    //database
    private val database = DatabaseOrdonnance.getInstance(application).ordonnanceDao

    var listOrdo = MutableLiveData<List<Ordonnance>>()

    val searchName=MutableLiveData<String>()


    init {

    }

    fun getAllOrdoByName() {//get ordo by name
        uiScope.launch {
            getAllOrdoByNameDatabase()
        }
    }

    private suspend fun getAllOrdoByNameDatabase() {
        withContext(Dispatchers.IO) {
            listOrdo.postValue(database.getAllOrdonnancesByName())
        }
    }

    fun getAllOrdoByDateEnd() {//get ordo by date end
        uiScope.launch {
            getAllOrdoByDateEndDatabase()
        }
    }

    private suspend fun getAllOrdoByDateEndDatabase() {
        withContext(Dispatchers.IO) {
            listOrdo.postValue(database.getAllOrdonnancesByDateEnd())
        }
    }

    fun getOrdoByNameQuery() {
        uiScope.launch {
            getOrdoByNameQueryDatabase()
        }
    }

    private suspend fun getOrdoByNameQueryDatabase() {
        withContext(Dispatchers.IO) {
            val name="%${searchName.value}%"
            listOrdo.postValue(database.getOrdoByNameQuery(name))
        }
    }


}
