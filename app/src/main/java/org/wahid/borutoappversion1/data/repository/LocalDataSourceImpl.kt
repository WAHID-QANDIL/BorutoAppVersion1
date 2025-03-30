package org.wahid.borutoappversion1.data.repository

import org.wahid.borutoappversion1.data.local.BorutoDatabase
import org.wahid.borutoappversion1.domain.model.Hero
import org.wahid.borutoappversion1.domain.repository.LocalDataSource


class LocalDataSourceImpl(
    borutoDatabase: BorutoDatabase
): LocalDataSource {
    val databaseDao = borutoDatabase.heroDao()
    override suspend fun getSelectedHero(heroId: Int): Hero {
        return databaseDao.getSelectedHero(heroId = heroId)
    }
}