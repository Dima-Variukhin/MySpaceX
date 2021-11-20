package com.example.myspacex.presentation

import androidx.annotation.IdRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myspacex.R
import com.example.myspacex.di.MainScreen
import com.example.myspacex.di.MainScreen.getLaunchesInteractorImpl
import com.example.myspacex.domain.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainScreenViewModel : ViewModel() {

    var searchState = MutableLiveData<Pair<Int, String?>>()
    var progressState = MutableLiveData<Boolean>()
    var errorState = MutableLiveData<Int>()

    private val interactor = getLaunchesInteractorImpl()
    private var job: Job? = null
    private var lastQuery: String = ""

    fun fetch(query: String) {
        viewModelScope.debounceLaunch(300) {
            lastQuery = query
            val inputDataValid = interactor.isInputDataValid(query)
            if (inputDataValid == true) {
                progressState.postValue(true)
                when (interactor.fetch(query)) {
                    Status.NO_RESULTS -> showScreenWithId(R.id.no_results)
                    Status.NO_CONNECTION -> showScreenWithId(R.id.no_connection)
                    Status.SERVICE_UNAVAILABLE -> showScreenWithId(R.id.service_unavailable)
                    Status.SUCCESS -> showScreenWithId(R.id.go_to_search_results)
                }
            } else if (inputDataValid == false) {
                errorState.postValue(R.string.invalid_input_message)
            }
        }
    }

    fun fetch() = fetch(lastQuery)
    private fun showScreenWithId(@IdRes id: Int) {
        progressState.postValue(false)
        searchState.postValue(Pair(id, lastQuery))
    }

    private fun CoroutineScope.debounceLaunch(
        time: Long,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        job?.cancel()
        return launch {
            delay(time)
            block()
        }.also {
            job = it
        }
    }
}