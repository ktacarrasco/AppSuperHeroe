package com.example.myappsuperheroe

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myappsuperheroe.pojo.SuperHeroe
import com.example.myappsuperheroe.ui.main.AdapterSH
import com.example.myappsuperheroe.ui.main.MainFragment
import com.example.myappsuperheroe.viewmodel.SHViewModel
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() , AdapterSH.MyClickListener {

 //  private val myClickListener: AdapterSH.MyClickListener? = null

    private var shList =  ArrayList<SuperHeroe>()

    private lateinit var viewAdapterSH: AdapterSH
    private lateinit var mViewModel: SHViewModel
    private lateinit var mFragment: MainFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
       /* if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view_tag, MainFragment.newInstance())
                    .commitNow()
        }*/
        //Iniciando el ViewModel
        mViewModel = ViewModelProvider(this).get(SHViewModel::class.java)
        // Iniciando el adapter
        viewAdapterSH = AdapterSH(shList,this)
        shRecyclerView.layoutManager = LinearLayoutManager(this)
        shRecyclerView.adapter = viewAdapterSH

        mViewModel.fetchFromServer()
        mViewModel.getDataFromDB().observe(this, Observer {
            Log.d("cant", it.toString())
            viewAdapterSH.updateData(it)


        })

    }

    override fun onItemClick(superHeroe: SuperHeroe) {

        Log.d("frag","esto pasa")
        shRecyclerView.visibility= View.GONE
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment, MainFragment.newInstance())
                .addToBackStack(null).commit()
    }


}
