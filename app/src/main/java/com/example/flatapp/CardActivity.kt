package com.example.flatapp

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide


class CardActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_one_item)

        val intent = intent
        val url = intent.getStringExtra("url")

        var image: ImageView = findViewById<ImageView>(R.id.url_image);
        Glide.with(this)
            .load(url)
            .into(image)
    }
}