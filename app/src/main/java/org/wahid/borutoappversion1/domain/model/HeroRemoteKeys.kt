package org.wahid.borutoappversion1.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.wahid.borutoappversion1.utils.Constants.HERO_REMOTE_KEY_DATABASE_TABLE_NAME

@Entity(tableName = HERO_REMOTE_KEY_DATABASE_TABLE_NAME)
data class HeroRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    val prevPage:Int?,
    val nextPage:Int?,
    val lastUpdated: Long?

)
