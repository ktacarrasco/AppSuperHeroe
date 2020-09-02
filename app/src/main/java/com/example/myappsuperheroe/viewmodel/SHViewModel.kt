package com.example.myappsuperheroe.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myappsuperheroe.pojo.SuperHeroe
import com.example.myappsuperheroe.remote.RepositorySH

class SHViewModel (application: Application) : AndroidViewModel(application) {

    private val repository =  RepositorySH(application)
    private val shList = repository.passLiveDataToViewModel()

    fun fetchFromServer() {
        repository.fetchDataFromServer()
    }

    fun getDataFromDB(): LiveData<List<SuperHeroe>> {
        return shList
    }


}