package com.lefebvre.thomas.newordomanager.ui.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Database
import com.lefebvre.thomas.newordomanager.database.DatabaseOrdonnance
import com.lefebvre.thomas.newordomanager.database.Ordonnance
import com.lefebvre.thomas.newordomanager.database.OrdonnanceDao
import kotlinx.coroutines.*
import java.util.*

class AddOrdoViewModel(application: Application) :
    AndroidViewModel(application) {
    //coroutine
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    //database
    private val database=DatabaseOrdonnance.getInstance(application).ordonnanceDao

    val name = MutableLiveData<String>()
    val firstName = MutableLiveData<String>()
    val dateStartLong = MutableLiveData<Long>()
    val dateEndLong = MutableLiveData<Long>()
    val durationInt = MutableLiveData<String>()

    fun insertOrdo() {
        uiScope.launch {
            val ordonnance=Ordonnance(System.currentTimeMillis(),name.value!!,firstName.value!!,dateStartLong.value!!,dateEndLong.value!!)
            insertOrdoDatabase(ordonnance)
        }
    }

    private suspend fun insertOrdoDatabase(ordonnance:Ordonnance){
       withContext(Dispatchers.IO){
           database.insert(ordonnance)
       }
    }


}
