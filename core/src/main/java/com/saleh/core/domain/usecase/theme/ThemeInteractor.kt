package com.saleh.core.domain.usecase.theme

import com.saleh.core.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class ThemeInteractor(val repository: IUserRepository): ThemeUseCase {
    override fun getThemeSetting(): Flow<Boolean> {
        return repository.getThemeSetting()
    }

    override fun saveThemeSetting(isDarkMode: Boolean) {
        repository.saveThemeSetting(isDarkMode)
    }
}