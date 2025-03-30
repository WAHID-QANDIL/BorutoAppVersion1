package org.wahid.borutoappversion1.domain.repository

import org.wahid.borutoappversion1.domain.model.Hero

interface LocalDataSource {
    suspend fun getSelectedHero(heroId: Int): Hero
}