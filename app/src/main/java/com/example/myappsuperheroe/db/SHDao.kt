package com.example.myappsuperheroe.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myappsuperheroe.pojo.SuperHero
@Dao
interface SHDao {

    //Insertar un listado
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSH(listSHES: List<SuperHero>)

    // Insertar 1 post
   /* @Insert
    suspend fun insertPost(post: Post)*/

    // traer todos los elementos de la tabla
    @Query("SELECT * FROM SH_table ")
    fun getAllSHList() : LiveData<List<SuperHero>>
}