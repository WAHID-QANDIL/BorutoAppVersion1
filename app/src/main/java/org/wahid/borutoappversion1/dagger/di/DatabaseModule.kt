package org.wahid.borutoappversion1.dagger.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.wahid.borutoappversion1.data.local.BorutoDatabase
import org.wahid.borutoappversion1.utils.Constants.DATABASE_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context:Context): BorutoDatabase = Room.databaseBuilder(
        context = context,
        klass = BorutoDatabase::class.java,
        name = DATABASE_NAME
    ).build()

}