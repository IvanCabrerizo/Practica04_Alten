package com.example.practica04.model

data class GameBo(
    val id: Int,
    val name: String,
    val studio: String,
    val launchDate: String,
    val compatiblePlatform: List<String>,
    val pegi: Int,
    val cover: String,
)