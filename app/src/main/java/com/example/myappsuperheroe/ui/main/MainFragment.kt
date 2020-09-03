package com.example.myappsuperheroe.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myappsuperheroe.R
import com.example.myappsuperheroe.pojo.SuperHero
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() , AdapterSH.MyClickListener{

    private var shList =  ArrayList<SuperHero>()

    private lateinit var viewAdapterSH: AdapterSH
    private lateinit var mViewModel: MainViewModel
    private lateinit var mFragment: MainFragment

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //Iniciando el ViewModel
        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // Iniciando el adapter
        viewAdapterSH = AdapterSH(shList,this)
        shRecyclerView.layoutManager = LinearLayoutManager(context)
        shRecyclerView.adapter = viewAdapterSH

        mViewModel.fetchFromServer()
        mViewModel.getDataFromDB().observe(viewLifecycleOwner, Observer {
            Log.d("cant", it.toString())
            viewAdapterSH.updateData(it)


        })
    }

    override fun onItemClick(superHero: SuperHero) {


    }
}