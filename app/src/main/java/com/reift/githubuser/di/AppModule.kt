package com.reift.githubuser.di

import com.reift.core.domain.usecase.detail.DetailInteractor
import com.reift.core.domain.usecase.detail.DetailUseCase
import com.reift.core.domain.usecase.followuser.FollowUserInteractor
import com.reift.core.domain.usecase.followuser.FollowUserUseCase
import com.reift.core.domain.usecase.search.SearchInteractor
import com.reift.core.domain.usecase.search.SearchUseCase
import com.reift.core.domain.usecase.theme.ThemeInteractor
import com.reift.core.domain.usecase.theme.ThemeUseCase
import com.reift.githubuser.presentation.detail.DetailViewModel
import com.reift.githubuser.presentation.followuser.FollowUserViewModel
import com.reift.githubuser.presentation.home.HomeViewModel
import com.reift.githubuser.presentation.home.ThemeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    single<FollowUserUseCase> { FollowUserInteractor(get()) }
    single<DetailUseCase> { DetailInteractor(get()) }
    single<SearchUseCase> { SearchInteractor(get()) }
    single<ThemeUseCase> { ThemeInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FollowUserViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { ThemeViewModel(get()) }
}