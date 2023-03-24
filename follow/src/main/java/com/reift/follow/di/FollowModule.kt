package com.reift.follow.di

import com.reift.core.domain.usecase.followuser.FollowUserInteractor
import com.reift.core.domain.usecase.followuser.FollowUserUseCase
import com.reift.follow.followuser.FollowUserViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    single<FollowUserUseCase> { FollowUserInteractor(get()) }
}

val viewModelModule = module {
    viewModel { FollowUserViewModel(get()) }
}