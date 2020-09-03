package com.example.myappsuperheroe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myappsuperheroe.pojo.SuperHero
import com.example.myappsuperheroe.ui.main.AdapterSH
import com.example.myappsuperheroe.ui.main.MainFragment

class MainActivity : AppCompatActivity() , AdapterSH.MyClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment.newInstance())
            .commitNow()

    }

    override fun onItemClick(superHero: SuperHero) {
        TODO("Not yet implemented")
    }
}
