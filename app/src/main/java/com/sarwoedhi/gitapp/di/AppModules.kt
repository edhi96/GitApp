package com.sarwoedhi.gitapp.di

import com.sarwoedhi.gitapp.data.source.api.GithubAPI
import com.sarwoedhi.gitapp.data.source.repo.AppRepository
import com.sarwoedhi.gitapp.ui.contributor.UserContributorViewModel
import com.sarwoedhi.gitapp.ui.search.SearchRepositoriesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModules = module {
    single { GithubAPI() }
    factory { AppRepository(get()) }
    viewModel { UserContributorViewModel(get()) }
    viewModel { SearchRepositoriesViewModel(get()) }
}