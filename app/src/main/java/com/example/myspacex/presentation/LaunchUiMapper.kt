package com.example.myspacex.presentation

import com.example.myspacex.data.LaunchData
import com.example.myspacex.data.cloud.Mapper

class LaunchUiMapper : Mapper<LaunchData, List<LaunchUi<*>>> {
    override fun map(source: LaunchData) =
        ArrayList<LaunchUi<*>>().apply {
            add(LaunchUi.MissionName(source.missionName))
            add(LaunchUi.FlightNumber(source.flightNumber.toString()))
            add(LaunchUi.LaunchYear(source.launchYear.toString()))
            if (!source.launchDate.isNullOrEmpty())
                add(LaunchUi.LaunchDate(source.launchDate!!))
            if (!source.details.isNullOrEmpty())
                add(LaunchUi.Details(source.details!!))

            val cores = source.rocket.firstStage.cores
            if (cores.isNotEmpty()) {
                val firstStageData = ArrayList<Pair<String, Boolean>>().apply {
                    for (core in cores) {
                        add(Pair(core.coreSerial, core.reused))
                    }
                }
                add(
                    LaunchUi.Rocket(
                        source.rocket.name,
                        source.rocket.type,
                        firstStageData,
                        source.rocket.secondStage
                    )
                )
            }

            if (source.ships.isNotEmpty()) {
                var ships = ""
                for (ship in source.ships) ships += ship + "\n"
                add(LaunchUi.Ships(ships))
            }

            add(LaunchUi.LaunchPlace(source.launchPlace))
            add(LaunchUi.LaunchSuccess(source.launchSuccess))

            source.links.forEach {
                add(LaunchUi.LinkTitle(it.title, it.address))
            }
            source.images.forEach {
                add(LaunchUi.Image(it.address))
            }
            source.PDFs.forEach {
                add(LaunchUi.PDF(it.title, it.address))
            }
        }
}