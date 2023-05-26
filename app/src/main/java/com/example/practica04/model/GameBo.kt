package com.example.practica04.model

enum class CompatiblePlatform(val platform: String) {
    ALL("Todas"),
    PLAYSTATION("PlayStation"),
    XBOX("Xbox"),
    NINTENDO("Nintendo");

    companion object {
        fun fromPlatform(platform: String): CompatiblePlatform {
            return when (platform) {
                PLAYSTATION.platform -> PLAYSTATION
                XBOX.platform -> XBOX
                NINTENDO.platform -> NINTENDO
                else -> ALL
            }
        }
    }
}

data class GameBo(
    val id: Int,
    val name: String,
    val studio: String,
    val launchDate: Int,
    val compatiblePlatform: List<CompatiblePlatform>,
    val pegi: Int,
    val cover: String,
)