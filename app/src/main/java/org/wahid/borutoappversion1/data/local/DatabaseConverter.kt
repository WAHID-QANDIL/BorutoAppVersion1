package org.wahid.borutoappversion1.data.local

import androidx.room.TypeConverter
import kotlin.text.StringBuilder

class DatabaseConverter {

    val seperator = ","


    @TypeConverter
    fun convertListToString(list: List<String>): String {

        val stringBuilder = StringBuilder()

        list.forEach { item ->
            stringBuilder.append(item)
            stringBuilder.append(seperator)
        }

        return stringBuilder.delete(stringBuilder.lastIndex - seperator.length, stringBuilder.lastIndex).toString()

    }

    @TypeConverter
    fun convertStringToList(string:String):List<String>
    {
        return string.split(seperator)
    }


}