package com.example.myappsuperheroe.pojo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "SH_table")
class SuperHeroe (@SerializedName("userId") @PrimaryKey val  id : Int,
                  val name: String,
                  @Embedded val images : Images) {


}