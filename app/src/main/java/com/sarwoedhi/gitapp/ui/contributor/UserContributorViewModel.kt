package com.sarwoedhi.gitapp.ui.contributor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarwoedhi.gitapp.data.models.GithubEntity
import com.sarwoedhi.gitapp.data.source.repo.AppRepository
import kotlinx.coroutines.launch

class UserContributorViewModel(private val appRepository: AppRepository) : ViewModel() {

    var data: LiveData<List<GithubEntity>> = MutableLiveData()

    init {
        getContributors()
    }

    private fun getContributors(): LiveData<List<GithubEntity>> {
        viewModelScope.launch {
            data = appRepository.getContributors()
        }
        return data
    }
}