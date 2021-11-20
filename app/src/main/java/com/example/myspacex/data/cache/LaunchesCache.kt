package com.example.myspacex.data.cache

import android.content.Context
import com.example.myspacex.data.cloud.LaunchesCloud
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface LaunchesCache {
    fun put(year: String, launches: List<LaunchesCloud>)
    fun get(year: String): List<LaunchesCloud>
    fun isCached(year: String): Boolean

    class Base(context: Context) : LaunchesCache {
        private val sharedPreferences = context.getSharedPreferences("cache", Context.MODE_PRIVATE)
        private val gson = Gson()

        override fun put(year: String, launches: List<LaunchesCloud>) {
            val json = gson.toJson(launches)
            sharedPreferences.edit().putString(year, json).apply()
        }

        override fun get(year: String): List<LaunchesCloud> {
            val json = sharedPreferences.getString(year, "")
            val type = object : TypeToken<List<LaunchesCloud>>() {
            }.type
            return Gson().fromJson(json, type)
        }

        override fun isCached(year: String): Boolean {
            val value = sharedPreferences.getString(year, null)
            return value?.isNotEmpty() ?: false
        }
    }
}