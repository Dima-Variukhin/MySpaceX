package com.example.myspacex.domain

import com.example.myspacex.data.LaunchData
import com.example.myspacex.data.LaunchesRepository

interface LaunchDetailsInteractor {
    suspend fun getLaunchData(year: String, position: Int): LaunchData
}

class LaunchDetailsInteractorImpl(
    private val repository: LaunchesRepository
) : LaunchDetailsInteractor {
    override suspend fun getLaunchData(year: String, position: Int) =
        repository.getLaunchData(year, position)
}
