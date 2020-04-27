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

class MainViewModel(application: Application) :
    AndroidViewModel(application) {
    //coroutine
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    //database
    private val database=DatabaseOrdonnance.getInstance(application).ordonnanceDao

    private var _listOrdo:LiveData<List<Ordonnance>>
    val listOrdo:LiveData<List<Ordonnance>>
    get() = _listOrdo


    init {
        _listOrdo=database.getAllOrdonnances()
    }

    fun getAllOrdoByName(){
        _listOrdo=database.getAllOrdonnancesByName()
    }

//    fun getListOrdo(){
//        uiScope.launch {
//            getListOrdoDatabase()
//        }
//    }
//
//    private suspend fun getListOrdoDatabase() {
//       withContext(Dispatchers.IO){
//          listOrdo.value = database.getOrderByName()
//       }
//    }


}
