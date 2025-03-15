package org.wahid.borutoappversion1.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.wahid.borutoappversion1.data.local.DataStoreInit
import org.wahid.borutoappversion1.data.repository.DatastoreOperationsImpl
import org.wahid.borutoappversion1.data.repository.Repository
import org.wahid.borutoappversion1.domain.repository.DatastoreOperations
import org.wahid.borutoappversion1.domain.use_cases.onboarding.ReadOnBoardingState
import org.wahid.borutoappversion1.domain.use_cases.onboarding.SaveOnBoardingUseCase
import org.wahid.borutoappversion1.domain.use_cases.onboarding.UseCases
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
    fun provideUseCases(repository: Repository):UseCases{
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository = repository),
            readOnBoardingState = ReadOnBoardingState(repository = repository)
        )
    }
}