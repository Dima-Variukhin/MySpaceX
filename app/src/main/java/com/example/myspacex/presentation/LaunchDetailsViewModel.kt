package com.example.myspacex.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myspacex.di.MainScreen
import kotlinx.coroutines.launch

class LaunchDetailsViewModel : ViewModel() {
    val launchData = MutableLiveData<List<LaunchUi<*>>>()

    private val interactor = MainScreen.getLaunchDetailsInteractor()
    private val mapper = LaunchUiMapper()

    fun showData(year: String, position: Int?) = viewModelScope.launch {
        launchData.value = mapper.map(interactor.getLaunchData(year, position ?: 0))
    }
}