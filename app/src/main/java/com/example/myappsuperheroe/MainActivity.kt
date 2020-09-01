package com.example.myappsuperheroe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myappsuperheroe.pojo.Images
import com.example.myappsuperheroe.pojo.SuperHeroe
import com.example.myappsuperheroe.remote.RetrofitClient
import com.example.myappsuperheroe.ui.main.AdapterImages
import com.example.myappsuperheroe.ui.main.AdapterSH
import com.example.myappsuperheroe.ui.main.MainFragment
import kotlinx.android.synthetic.main.main_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var photoList =  ArrayList<SuperHeroe>()
    private var imagesList =  ArrayList<Images>()
    private lateinit var viewAdapterSH: AdapterSH
    private lateinit var viewAdapterImages: AdapterImages

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        /*if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view_tag, MainFragment.newInstance())
                    .commitNow()
        }*/

        viewAdapterSH = AdapterSH(photoList)
        shRecyclerView.adapter = viewAdapterSH


        loadApiPhoto()



    }

        private fun loadApiPhoto() {
            val service = RetrofitClient.retrofitInstance()
            val call = service.getPhotos()

            call.enqueue(object : Callback<List<SuperHeroe>> {
                override fun onFailure(call: Call<List<SuperHeroe>>, t: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        "Error: no se logro recuperar las imagenes desde la api",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onResponse(call: Call<List<SuperHeroe>>, response: Response<List<SuperHeroe>>) {
                    Log.d("Error",response.body().toString())
                    response.body()?.map { photoList.add(it) }
                    viewAdapterSH.notifyDataSetChanged()
                }

            })
        }



   /* private fun loadApiImages() {
        val service = RetrofitClient.retrofitInstance()
        val call= service.getImages()

        call.enqueue(object : Callback<List<Images>> {
            override fun onFailure(call: Call<List<Images>>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    "Error: no se logro recuperar los usuarios desde la api",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(call: Call<List<Images>>, response: Response<List<Images>>) {
                response.body()?.map { imagesList.add(it) }
                viewAdapterImages.notifyDataSetChanged()
            }

        })
    }*/

    }
