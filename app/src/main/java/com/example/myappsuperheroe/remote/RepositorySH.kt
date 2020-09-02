package com.example.myappsuperheroe.remote

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.myappsuperheroe.db.SHRoomDB
import com.example.myappsuperheroe.pojo.SuperHeroe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositorySH(context: Context) {


    private val tag = "SHRepository"

    //esto viene  de la Base de datos
    private val db: SHRoomDB = SHRoomDB.getDatabase(context)
    private val shList = db.shDao().getAllSHList()


    fun passLiveDataToViewModel(): LiveData<List<SuperHeroe>> {
        return shList
    }

    // esto hace la llamada a retrofit
    fun fetchDataFromServer() {
        val service = RetrofitClient.retrofitInstance()
        val call = service.getPhotos()


        call.enqueue(object : Callback<List<SuperHeroe>> {
            override fun onResponse(call: Call<List<SuperHeroe>>, response: Response<List<SuperHeroe>>) {
                Log.d(tag, response.body().toString())
                CoroutineScope(Dispatchers.IO).launch {
                    response.body()?.let { db.shDao().insertAllSH(it) }
                }
            }

            override fun onFailure(call: Call<List<SuperHeroe>>, t: Throwable) {
                Log.d(tag, t.message.toString())
            }
        })

    }
}