package com.example.myspacex.domain

import com.example.myspacex.data.LaunchData
import com.example.myspacex.data.LaunchesRepository

interface SearchResultsInteractor {
    suspend fun getSearchResults(year: String): List<LaunchData>

    class Base(
        private val launchesRepository: LaunchesRepository
    ) : SearchResultsInteractor {
        override suspend fun getSearchResults(year: String) = launchesRepository.getLaunches(year)
    }
}