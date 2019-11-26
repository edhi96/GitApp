package com.sarwoedhi.gitapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarwoedhi.gitapp.data.models.SearchEntity
import com.sarwoedhi.gitapp.data.source.repo.AppRepository
import kotlinx.coroutines.launch

class SearchRepositoriesViewModel(private val appRepository: AppRepository) : ViewModel() {
    private var dataSearch: LiveData<List<SearchEntity>> = MutableLiveData()

    fun getResultSearch(query: String): LiveData<List<SearchEntity>> {
        getSearch(query = query)
        return dataSearch
    }

    private fun getSearch(query: String): LiveData<List<SearchEntity>> {
        viewModelScope.launch {
            dataSearch = appRepository.getSearch(query)
        }
        return dataSearch
    }

}