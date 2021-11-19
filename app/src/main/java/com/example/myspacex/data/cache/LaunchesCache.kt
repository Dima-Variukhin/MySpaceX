package com.example.myspacex.data.cache

import com.example.myspacex.data.cloud.LaunchesCloud

interface LaunchesCache {
    fun put(year: String, launches: List<LaunchesCloud>)
    fun get(year: String): List<LaunchesCloud>
    fun isCached(year: String): Boolean
}