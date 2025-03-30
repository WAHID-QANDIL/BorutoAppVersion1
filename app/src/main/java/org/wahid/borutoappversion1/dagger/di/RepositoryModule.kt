package org.wahid.borutoappversion1.dagger.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.wahid.borutoappversion1.data.local.BorutoDatabase
import org.wahid.borutoappversion1.data.local.DataStoreInit
import org.wahid.borutoappversion1.data.repository.DatastoreOperationsImpl
import org.wahid.borutoappversion1.data.repository.LocalDataSourceImpl
import org.wahid.borutoappversion1.data.repository.Repository
import org.wahid.borutoappversion1.domain.repository.DatastoreOperations
import org.wahid.borutoappversion1.domain.repository.LocalDataSource
import org.wahid.borutoappversion1.domain.use_cases.onboarding.ReadOnBoardingState
import org.wahid.borutoappversion1.domain.use_cases.onboarding.SaveOnBoardingUseCase
import org.wahid.borutoappversion1.domain.use_cases.UseCases
import org.wahid.borutoappversion1.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import org.wahid.borutoappversion1.domain.use_cases.get_selected_hero.GetSelectedHero
import org.wahid.borutoappversion1.domain.use_cases.search_heroes.SearchHeroes
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context):
            DatastoreOperations = DatastoreOperationsImpl(DataStoreInit(context = context))

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository = repository),
            readOnBoardingState = ReadOnBoardingState(repository = repository),
            getAllHeroesUseCase = GetAllHeroesUseCase(repository = repository),
            searchHeroes = SearchHeroes(repository = repository),
            getSelectedHero = GetSelectedHero(repository = repository)
        )
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(borutoDatabase: BorutoDatabase): LocalDataSource {
        return LocalDataSourceImpl(
            borutoDatabase = borutoDatabase
        )
    }

}