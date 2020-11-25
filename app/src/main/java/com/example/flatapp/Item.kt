package com.example.flatapp

import android.media.Image
import android.widget.ImageView

data class Item(
    val id: Int,
    val images: List<Image>,
    val location: Location,
    val price: Int,
    val title: String
)