package com.example.myspacex.domain

import com.example.myspacex.data.LaunchesRepository
import com.example.myspacex.data.datasource.NetworkConnectionException
import com.example.myspacex.domain.validator.Validator
import java.lang.Exception
import java.util.*

interface LaunchesInteractor {
    fun isInputDataValid(year: String?): Boolean?
    suspend fun fetch(year: String): Status

    class Base(
        private val repository: LaunchesRepository,
        private val yearValidator: Validator.Base
    ) : LaunchesInteractor {
        override fun isInputDataValid(year: String?) = yearValidator.isValid(year)

        override suspend fun fetch(year: String): Status {
            val reload = Calendar.getInstance().get(Calendar.YEAR).toString() == year
            return getData(year, reload, 3)
        }

        private suspend fun getData(year: String, reload: Boolean, retryCount: Int): Status =
            try {
                val list = repository.getLaunches(year, reload)
                if (list.isEmpty())
                    Status.NO_RESULTS
                else
                    Status.SUCCESS
            } catch (e: Exception) {
                e.printStackTrace()
                if (retryCount > 0) {
                    getData(year, retryCount > 1, retryCount - 1)
                } else {
                    if (e is NetworkConnectionException)
                        Status.NO_CONNECTION
                    else
                        Status.SERVICE_UNAVAILABLE
                }
            }
    }
}

enum class Status {
    NO_RESULTS,
    SERVICE_UNAVAILABLE,
    NO_CONNECTION,
    SUCCESS
}