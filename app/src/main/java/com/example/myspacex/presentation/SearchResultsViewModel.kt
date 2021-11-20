package com.example.myspacex.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myspacex.di.MainScreen.getSearchResultsInteractor
import kotlinx.coroutines.launch

class SearchResultsViewModel : ViewModel() {
    val results = MutableLiveData<List<String>>()

    private val interactor = getSearchResultsInteractor()
    fun showResults(year: String) = viewModelScope.launch {
        results.value = interactor.getSearchResults(year).map { it.missionName }
    }
}