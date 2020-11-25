package com.example.flatapp

import android.content.Context
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

data class ReadJSON(
    val items: List<Item>
)
