package com.reift.githubuser.di

val useCaseModule = module {
    factory<TourismUseCase> { TourismInteractor(get()) }
    factory<TourismUseCase> { TourismInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailTourismViewModel(get()) }
}