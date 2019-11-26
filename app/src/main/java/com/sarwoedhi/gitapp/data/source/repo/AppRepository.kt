package com.sarwoedhi.gitapp.data.source.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sarwoedhi.gitapp.data.models.GithubEntity
import com.sarwoedhi.gitapp.data.models.SearchEntity
import com.sarwoedhi.gitapp.data.models.SearchResponse
import com.sarwoedhi.gitapp.data.source.api.GithubAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.awaitResponse

class AppRepository(private val apiService: GithubAPI) {

    private lateinit var mUserList: MutableLiveData<List<GithubEntity>>
    private lateinit var searchResultList: MutableLiveData<List<SearchEntity>>

    suspend fun getContributors(): LiveData<List<GithubEntity>> {
        mUserList = MutableLiveData()
        val usersContributor: ArrayList<GithubEntity> = arrayListOf()
        CoroutineScope(Dispatchers.IO).launch {
            try {

                val call: Call<List<GithubEntity>> =
                    apiService.getContributors("square", "retrofit")
                val response = call.awaitResponse()
                val body = response.body()
                if (body != null) {
                    for (i in body.iterator()) {
                        usersContributor.add(i)
                    }
                    mUserList.postValue(usersContributor)
                }
            } catch (e: Exception) {
            }
        }
        return mUserList
    }

    suspend fun getSearch(query: String): LiveData<List<SearchEntity>> {
        searchResultList = MutableLiveData()
        CoroutineScope(Dispatchers.IO).launch {
            val call: Call<SearchResponse> = apiService.getSearch(query)
            val itemList: ArrayList<SearchEntity> = arrayListOf()
            try {
                val response = call.awaitResponse()
                val body = response.body()
                if (response.isSuccessful) {
                    if (body != null) {
                        for (i in body.items.iterator()) {
                            itemList.add(i)
                        }
                    }
                }
                searchResultList.postValue(itemList)
            } catch (e: Exception) {

            }
        }
        return searchResultList
    }
}