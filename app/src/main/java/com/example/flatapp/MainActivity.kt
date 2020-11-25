package com.example.flatapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.io.InputStream
import java.lang.Exception

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var jsonString = loadJson(this)

        var json = jsonString?.let { jsonResult(it) }
    }

    private fun loadJson(context: Context): String? {
        var input: InputStream? = null
        var jsonString: String

        try {
            // Create InputStream
            input = resources.openRawResource(R.raw.json)

            val size = input.available()

            // Create a buffer with the size
            val buffer = ByteArray(size)

            // Read data from InputStream into the Buffer
            input.read(buffer)

            // Create a json String
            jsonString = String(buffer)
            return jsonString
        } catch (ex: Exception) {
            ex.printStackTrace()
        } finally {
            // Must close the stream
            input?.close()
        }

        return null
    }

    private fun convert(jsonFile: String?): String {
        val mapper = jacksonObjectMapper()
        mapper.configure( DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true )

        val item = jsonFile?.let { mapper.readValue<Item>(it) }

        return item.toString()
    }

    private fun jsonResult(jsonString: String) {
        val jsonArray = JSONArray(jsonString)

        val list = ArrayList<Item>()
        var i = 0
        while (i < jsonArray.length())
        {
            val jsonObject = jsonArray.getJSONObject(i)
            list.add(Item(
                jsonObject.getInt("id"),
                jsonObject.getString("title"),
                jsonObject.getInt("price")
                //jsonObject.getString("location")
            ))
            i++
        }
        val adapter = ListAdapter(this, list)
        mylist.adapter = adapter
    }

}
