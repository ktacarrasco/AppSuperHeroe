package com.example.myappsuperheroe.remote

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.myappsuperheroe.db.SHRoomDB
import com.example.myappsuperheroe.pojo.SuperHero
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
    // private val shId = db.shDao().getIdSHList( id:Int)


    fun  passIdtoFragment(id :Int):LiveData<SuperHero>{

        return  db.shDao().getIdSHList(id)
    }

    fun passLiveDataToViewModel(): LiveData<List<SuperHero>> {
        return shList
    }

    // esto hace la llamada a retrofit
    fun fetchDataFromServer() {
        val service = RetrofitClient.retrofitInstance()
        val call = service.getPhotos()


        call.enqueue(object : Callback<List<SuperHero>> {
            override fun onResponse(call: Call<List<SuperHero>>, response: Response<List<SuperHero>>) {
                Log.d(tag, response.body().toString())
                CoroutineScope(Dispatchers.IO).launch {
                    //response.body()?.let { db.shDao().insertAllSH(it) }
                    db.shDao().insertAllSH(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<SuperHero>>, t: Throwable) {
                Log.d(tag, t.message.toString())

            }
        })

    }
}