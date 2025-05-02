package com.android.basketballapp.di

import com.android.basketballapp.data.remote.api.GameApi
import com.android.basketballapp.data.repository.GameRepositoryImpl
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
import com.android.basketballapp.data.repository.GameRepository

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://api.balldontlie.io/v1/"
    private const val API_KEY = "acef2fd2-7c72-4a3d-a0cb-c01fc387b1b9" // ✅ Replace with actual API key

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $API_KEY") // ✅ Corrected Authorization header
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideGameApi(okHttpClient: OkHttpClient): GameApi {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(GameApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGameRepository(api: GameApi): GameRepository {
        return GameRepositoryImpl(api)
    }
}