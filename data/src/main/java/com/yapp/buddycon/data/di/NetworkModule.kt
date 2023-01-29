package com.yapp.buddycon.data.di

import com.yapp.buddycon.data.network.*
import com.yapp.buddycon.data.network.api.LoginService
import com.yapp.buddycon.data.network.api.RefreshTokenService
import com.yapp.buddycon.data.network.qualifiers.BuddyConRetrofit
import com.yapp.buddycon.data.network.qualifiers.LoginRetrofit
import com.yapp.buddycon.data.network.qualifiers.RefreshTokenRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "http://3.36.52.192:8080/"

    @LoginClient
    @Provides
    @Singleton
    fun provideLoginClient(
        @HttpLoggingInterceptorQualifier httpLoggingInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()


    @RefreshTokenClient
    @Provides
    @Singleton
    fun provideRefreshTokenClient(
        @HttpLoggingInterceptorQualifier httpLoggingInterceptor: Interceptor,
        @RefreshTokenInterceptorQualifier refreshTokenInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(refreshTokenInterceptor)
            .build()


    @BuddyConClient
    @Provides
    @Singleton
    fun provideBuddyConClient(
        @HttpLoggingInterceptorQualifier httpLoggingInterceptor: Interceptor,
        @BuddyConInterceptorQualifier buddyConInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(buddyConInterceptor)
            .build()


    @LoginRetrofit
    @Provides
    @Singleton
    fun provideLoginRetrofit(
        @LoginClient okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @RefreshTokenRetrofit
    @Provides
    @Singleton
    fun provideRefreshTokenRetrofit(
        @RefreshTokenClient okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @BuddyConRetrofit
    @Provides
    @Singleton
    fun provideBuddyConRetrofit(
        @BuddyConClient okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun provideLoginService(
        @LoginRetrofit retrofit: Retrofit
    ): LoginService =
        retrofit.create()


    @Provides
    @Singleton
    fun provideTokenService(
        @RefreshTokenRetrofit retrofit: Retrofit
    ): RefreshTokenService =
        retrofit.create()
}