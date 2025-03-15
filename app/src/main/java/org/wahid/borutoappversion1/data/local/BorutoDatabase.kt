package org.wahid.borutoappversion1.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.wahid.borutoappversion1.data.local.dao.HeroDao
import org.wahid.borutoappversion1.data.local.dao.HeroRemoteKeyDao
import org.wahid.borutoappversion1.domain.model.Hero
import org.wahid.borutoappversion1.domain.model.HeroRemoteKey


@Database(
    entities = [Hero::class,HeroRemoteKey::class],
    version = 1
)
@TypeConverters(DatabaseConverter::class)
abstract class BorutoDatabase: RoomDatabase() {

    abstract fun heroDao():HeroDao
    abstract fun heroRemoteKeyDao():HeroRemoteKeyDao
}