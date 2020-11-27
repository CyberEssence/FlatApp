package com.example.flatapp

data class Item(
    val id: Int,
    val title: String,
    val price: Int,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val url: String
)