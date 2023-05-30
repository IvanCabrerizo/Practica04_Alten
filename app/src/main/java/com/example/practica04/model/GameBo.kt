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

enum class Pegi {
    PEGI3,
    PEGI7,
    PEGI12,
    PEGI16,
    PEGI18,
}

data class GameBo(
    val id: Int,
    val name: String,
    val studio: String,
    val launchDate: Int,
    val compatiblePlatform: List<CompatiblePlatform>,
    val pegi: Pegi,
    val cover: String,
)