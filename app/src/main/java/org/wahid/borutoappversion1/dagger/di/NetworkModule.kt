package org.wahid.borutoappversion1.dagger.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.wahid.borutoappversion1.data.local.BorutoDatabase
import org.wahid.borutoappversion1.data.remote.retrofit.BorutoApi
import org.wahid.borutoappversion1.data.repository.RemoteDataSourceImpl
import org.wahid.borutoappversion1.domain.repository.RemoteDataSource
import org.wahid.borutoappversion1.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun provideBorutoApi(retrofit: Retrofit): BorutoApi = retrofit.create(BorutoApi::class.java)


    @Provides
    @Singleton
    fun provideRemoteDataSource(
        borutoApi: BorutoApi,
        borutoDatabase: BorutoDatabase,
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            borutoApi = borutoApi,
            borutoDatabase = borutoDatabase
        )
    }


}