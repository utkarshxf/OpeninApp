package com.orion.templete.di

import com.orion.templete.data.network.ApiService
import com.orion.templete.data.network.ApiService.Companion.BASE_URL
import com.orion.templete.data.network.ApiService.Companion.BEARER_TOKEN
import com.orion.templete.data.repository.GetDashboardImplementation
import com.orion.templete.domain.repository.GetDashboardRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val newRequest: Request = originalRequest.newBuilder()
                    .header("Authorization", BEARER_TOKEN)
                    .build()
                chain.proceed(newRequest)
            }
            .build()
    }
    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return try {
            Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        } catch (e : IllegalArgumentException) {
            throw e
        }
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideRepository(apiService: ApiService): GetDashboardRepository {
        return GetDashboardImplementation(apiService = apiService)
    }
}
