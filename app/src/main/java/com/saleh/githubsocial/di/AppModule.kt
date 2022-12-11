package com.saleh.githubsocial.di

import com.saleh.core.domain.usecase.detail.DetailInteractor
import com.saleh.core.domain.usecase.detail.DetailUseCase
import com.saleh.core.domain.usecase.followuser.FollowUserInteractor
import com.saleh.core.domain.usecase.followuser.FollowUserUseCase
import com.saleh.core.domain.usecase.search.SearchInteractor
import com.saleh.core.domain.usecase.search.SearchUseCase
import com.saleh.core.domain.usecase.theme.ThemeInteractor
import com.saleh.core.domain.usecase.theme.ThemeUseCase
import com.saleh.githubsocial.presentation.detail.DetailViewModel
import com.saleh.githubsocial.presentation.followuser.FollowUserViewModel
import com.saleh.githubsocial.presentation.home.HomeViewModel
import com.saleh.githubsocial.presentation.home.ThemeViewModel
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