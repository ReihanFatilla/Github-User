package com.reift.follow.di

import com.reift.core.domain.usecase.followuser.FollowUserInteractor
import com.reift.core.domain.usecase.followuser.FollowUserUseCase
import com.reift.follow.followuser.FollowUserViewModel
import com.reift.githubuser.presentation.detail.DetailViewModel
import com.reift.githubuser.presentation.home.HomeViewModel
import com.reift.githubuser.presentation.home.ThemeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    single<FollowUserUseCase> { FollowUserInteractor(get()) }
}

val viewModelModule = module {
    viewModel { FollowUserViewModel(get()) }
}