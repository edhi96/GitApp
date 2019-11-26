package com.sarwoedhi.gitapp.data.source.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.sarwoedhi.gitapp.BuildConfig.BASE_URL
import com.sarwoedhi.gitapp.data.models.GithubEntity
import com.sarwoedhi.gitapp.data.models.SearchResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubAPI {

    @GET("repos/{owner}/{repo}/contributors")
    fun getContributors(@Path("owner") ownerRepo: String, @Path("repo") repo: String): Call<List<GithubEntity>>

    @GET("/search/repositories")
    fun getSearch(@Query("q") searchQuery: String): Call<SearchResponse>

    companion object {
        operator fun invoke(): GithubAPI {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubAPI::class.java)
        }


    }
}