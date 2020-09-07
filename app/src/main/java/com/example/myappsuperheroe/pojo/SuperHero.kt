package com.example.myappsuperheroe.pojo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "SH_table")
data class SuperHero (@SerializedName("userId") @PrimaryKey(autoGenerate = true) val  id : Int,
                      val name: String,
                      @Embedded val appearance: Appearance?,
                      @Embedded val images : Images) {


}