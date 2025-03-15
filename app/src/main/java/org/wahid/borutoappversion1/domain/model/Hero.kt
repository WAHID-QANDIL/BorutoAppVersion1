package org.wahid.borutoappversion1.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.wahid.borutoappversion1.utils.Constants.HERO_DATABASE_TABLE_NAME


@Entity(tableName = HERO_DATABASE_TABLE_NAME)
data class Hero(
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    val name:String,
    val image:String,
    val about:String,
    val rating:Double,
    val power:Int,
    val month:String,
    val day:String,
    val family:List<String>,
    val abilities:List<String>,
    val natureTypes:List<String>
)