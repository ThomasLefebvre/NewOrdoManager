package com.lefebvre.thomas.newordomanager.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class MainViewModel : ViewModel() {

    val name = MutableLiveData<String>()
    val firstName = MutableLiveData<String>()
    val dateStartString = MutableLiveData<String>()
    val dateStartLong = MutableLiveData<Long>()
    val duration = MutableLiveData<Long>()
    val dateEndString = MutableLiveData<String>()
    val dateEndLong = MutableLiveData<Long>()
}
