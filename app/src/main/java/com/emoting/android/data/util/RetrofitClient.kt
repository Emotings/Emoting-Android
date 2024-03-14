package com.emoting.android.data.util

import com.emoting.android.data.api.AuthApi
import com.emoting.android.data.api.RequestUrl
import com.emoting.android.data.api.UserApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object RetrofitClient {
    private val tokenInterceptor = TokenInterceptor()

    private val httpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(tokenInterceptor)
        .build()

    private val gsonConverterFactory = GsonConverterFactory.create()

    private val retrofit = Retrofit.Builder().client(okHttpClient)
        .baseUrl(RequestUrl.BASE_URL)
        .addConverterFactory(gsonConverterFactory)
        .build()

    fun getAuthApi() = retrofit.create(AuthApi::class.java)

    fun getUserApi() = retrofit.create(UserApi::class.java)
}
