package com.example.myspacex.data.datasource

import com.example.myspacex.data.cloud.LaunchesCloud

interface LaunchesDataStore {
    suspend fun getLaunchCloudList(year: String): List<LaunchesCloud>
    suspend fun getLaunchDetails(year: String, id: Int): LaunchesCloud
}