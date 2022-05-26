package com.rk.utility22.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rk.utility22.MainViewModel.MainViewModel
import com.rk.utility22.repository.repository

class MainViewModelFactory (private val repository: repository): ViewModelProvider.Factory {
        override fun < T: ViewModel> create(modelClass: Class<T>):T{
            return MainViewModel(repository) as T
        }
}