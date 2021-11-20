package com.example.myspacex.presentation

import com.example.myspacex.data.SecondStageData

sealed class LaunchUi<T> {
    abstract val value: T

    data class FlightNumber(override val value: String) : LaunchUi<String>()
    data class MissionName(override val value: String) : LaunchUi<String>()
    data class LaunchYear(override val value: String) : LaunchUi<String>()
    data class LaunchDate(override val value: String) : LaunchUi<String>()
    data class Rocket(
        override val value: String,
        val type: String,
        val firstStageData: List<Pair<String, Boolean>>,
        val secondStage: SecondStageData
    ) : LaunchUi<String>()

    data class Ships(override val value: String) : LaunchUi<String>()
    data class LaunchPlace(override val value: String) : LaunchUi<String>()
    data class LaunchSuccess(override val value: Boolean) : LaunchUi<Boolean>()
    data class LinkTitle(val title: String, override val value: String) : LaunchUi<String>()
    data class Image(override val value: String) : LaunchUi<String>()
    data class PDF(val title: String, override val value: String) : LaunchUi<String>()
    data class Details(override val value: String) : LaunchUi<String>()

}