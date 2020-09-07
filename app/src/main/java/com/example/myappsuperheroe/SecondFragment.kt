package com.example.myappsuperheroe

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.myappsuperheroe.pojo.SuperHero
import com.example.myappsuperheroe.ui.main.AdapterSH
import com.example.myappsuperheroe.ui.main.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.second_fragment.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "id"
private const val ARG_PARAM2 = "name"
private const val ARG_PARAM3 = "image"


private var shListItem :  List<SuperHero> = ArrayList<SuperHero>()

private lateinit var viewAdapterSH: AdapterSH
private lateinit var mViewModel: MainViewModel
private lateinit var mFragment: SecondFragment
private lateinit var shRecyclerView: RecyclerView
private var textdetail: TextView? = null
private var imagedetail: ImageView? = null


class SecondFragment : Fragment() , AdapterSH.IAdapter {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var param2: String? = null
    private var param3: String? = null

    // private const val ARG_PARAM2 = "images"

    private lateinit var mViewModel: MainViewModel
    private lateinit var mFragment: SecondFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getString(ARG_PARAM3)

            //Iniciando el ViewModel
            mViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)


        }
    }

    @SuppressLint("FragmentLiveDataObserve", "SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view :View=inflater.inflate(R.layout.second_fragment, container, false)


        param1?.let {
            mViewModel.getIdDataFromDB(it).observe(viewLifecycleOwner, Observer {
                Log.d("cant", it.toString())

                //viewAdapterSH.updateData(it)

                view.titleTV.text = "Super Hero: ${it.name}"
                //view.titleTV.text = it.name
                if(it.appearance?.gender != null){
                    Log.d("apaGenero",it.appearance.gender)
                    view.apparanceTV.text = "Gender: ${it.appearance!!.gender}"}

                view.apparance2TV.text = "Eye Color: ${it.appearance!!.eyeColor}"
                view.apparance3TV.text = "Hair Color: ${it.appearance!!.hairColor}"
                Picasso.get()
                    .load(it.images.sm)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(view.photoTV)

            })
        }
        Log.d("cantSF2", context.toString())

        Log.d("cantF223", shListItem.toString())


        // view.titleTV.text = param2.toString()

        return view
    }



    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {

                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun getFromAdapter(id:  Int) {
        mViewModel.getDataFromDB(id)
    }
}



