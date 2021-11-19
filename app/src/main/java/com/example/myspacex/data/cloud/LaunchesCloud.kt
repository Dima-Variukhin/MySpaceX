package com.example.myspacex.data.cloud

import com.google.gson.annotations.SerializedName

data class LaunchesCloud(
    @SerializedName("flight_number") val flightNumber: Int,
    @SerializedName("mission_name") val missionName: String,
    @SerializedName("launch_year") val launchYear: Int,
    @SerializedName("launch_date_utc") val launchDateUTC: String,
    @SerializedName("rocket") val rocket: RocketCloud,
    @SerializedName("ships") val ships: List<String>,
    @SerializedName("telemetry") val telemetry: Map<String?, String?>,
    @SerializedName("reuse") val reuse: Map<String, Boolean>,
    @SerializedName("launch_site") val launchSite: LaunchSiteCloud,
    @SerializedName("launch_success") val launchSuccess: Boolean,
    @SerializedName("links") val links: Map<String, Any>,
    @SerializedName("details") val details: String?,
    @SerializedName("upcoming") val upcoming: Boolean,
    @SerializedName("static_fire_date_utc") val staticFireDateUTC: String,
    @SerializedName("timeline") val timeline: Map<String, Int>?
)

data class RocketCloud(
    @SerializedName("rocket_name") val name: String,
    @SerializedName("rocket_type") val type: String,
    @SerializedName("first_stage") val firstStage: FirstStageCloud,
    @SerializedName("second_stage") val secondStage: SecondStageCloud
)

data class FirstStageCloud(@SerializedName("cores") val cores: List<CoreCloud>)

data class SecondStageCloud(
    @SerializedName("block") val block: Int,
    @SerializedName("payloads") val payloads: List<PayloadCloud>
)

data class CoreCloud(
    @SerializedName("core_serial") val coreSerial: String,
    @SerializedName("reused") val reused: Boolean
)

data class PayloadCloud(
    @SerializedName("manufacturer") val manufacturer: String?,
    @SerializedName("nationality") val nationality: String?,
    @SerializedName("payload_type") val payloadType: String?,
    @SerializedName("payload_mass_kg") val payloadMassKg: Double?,
    @SerializedName("orbit") val orbit: String?
)

data class LaunchSiteCloud(
    @SerializedName("site_name_long") val longName: String
)
