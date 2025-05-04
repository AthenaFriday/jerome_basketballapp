// AppModule.kt
package com.android.basketballapp.di

import com.android.basketballapp.data.remote.api.GameApi
import com.android.basketballapp.data.remote.api.PlayerApi
import com.android.basketballapp.data.remote.api.TeamApi
import com.android.basketballapp.data.repository.GameRepository
import com.android.basketballapp.data.repository.GameRepositoryImpl
import com.android.basketballapp.data.repository.PlayerRepository
import com.android.basketballapp.data.repository.PlayerRepositoryImpl
import com.android.basketballapp.data.repository.TeamRepository
import com.android.basketballapp.data.repository.TeamRepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://api.balldontlie.io/v1/"
    private const val API_KEY = "acef2fd2-7c72-4a3d-a0cb-c01fc387b1b9"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $API_KEY")
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    @Singleton
    fun provideGameApi(retrofit: Retrofit): GameApi =
        retrofit.create(GameApi::class.java)

    @Provides
    @Singleton
    fun provideGameRepository(api: GameApi): GameRepository =
        GameRepositoryImpl(api)

    @Provides
    @Singleton
    fun providePlayerApi(retrofit: Retrofit): PlayerApi =
        retrofit.create(PlayerApi::class.java)

    @Provides
    @Singleton
    fun providePlayerRepository(api: PlayerApi): PlayerRepository =
        PlayerRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideTeamApi(retrofit: Retrofit): TeamApi =
        retrofit.create(TeamApi::class.java)

    @Provides
    @Singleton
    fun provideTeamRepository(api: TeamApi): TeamRepository =
        TeamRepositoryImpl(api)

}