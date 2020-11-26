package com.example.flatapp

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    val list = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jsonString = loadJson(this)

        var json = jsonString?.let { jsonResult(it) }
        val url: String = list[0].url


        //Picasso.get().load(url).into(url_image)

    }

    private fun loadJson(context: Context): String? {
        var input: InputStream? = null
        val jsonString: String

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



    private fun jsonResult(jsonString: String) {
        val jsonObject: JSONObject = JSONObject(jsonString)
        val items: JSONArray = jsonObject.getJSONArray("items")
        var i = 0
        while (i < items.length())
        {
            val c: JSONObject = items.getJSONObject(i)
            val arr: JSONArray = c.getJSONArray("images")
            val arrObj: JSONObject = arr.getJSONObject(i)
            val loc: JSONObject = c.getJSONObject("location")
            var j = 0



            list.add(
                Item(
                    c.getInt("id"),
                    c.getString("title"),
                    c.getInt("price"),
                    loc.getString("address") + " " +
                            loc.getDouble("latitude").toString() + " " +
                            loc.getDouble("longitude").toString(),
                    arrObj.getString("url")
                    )
                )
            i++
        }
        val adapter = RecyclerAdapter(list)
        my_list.adapter = adapter
    }

}
