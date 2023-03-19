package com.example.education

import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import java.io.*
import java.nio.channels.FileChannel
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val asmenys = ArrayList<AsmenysModel>()
        try {
            //val read_json = assets.open("data.json").bufferedReader().use { it.readText() }

            val obj = JSONObject(readFile())
            val jsonArray = obj.getJSONArray("asmenys")
            val pomegiaiArray = ArrayList<String>()

            for (i in 0 until jsonArray.length()) {
                val asmenys_dtl = jsonArray.getJSONObject(i)
                val vardas = asmenys_dtl.getString("vardas")
                val amzius = asmenys_dtl.getInt("amzius")
                val lytis = asmenys_dtl.getString("lytis")

                println("vardas: $vardas")
                val pomegiaiJSONArray = asmenys_dtl.getJSONArray("pomegiai")
                pomegiaiArray.clear()

                for (j in 0 until pomegiaiJSONArray.length()) {
                    pomegiaiArray.add(pomegiaiJSONArray.getString(j))
                    println("pomegiai: $pomegiaiArray")
                }

                val asmenys_list = AsmenysModel(vardas, amzius, lytis, pomegiaiArray)
                asmenys.add(asmenys_list)
                println("pomegiai1: $pomegiaiArray")
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }

        val rvAsmenys = findViewById<RecyclerView>(R.id.rvAsmenys)
        rvAsmenys.layoutManager = LinearLayoutManager(this)
        val itemAdapter = Adapter(this,asmenys)
        rvAsmenys.adapter = itemAdapter
    }

    private fun readFile(): String {
        /* Internal Storage
        val context = applicationContext
        val file = context.filesDir.absolutePath + "/newData.json"
        */

        val file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).absolutePath + "/newData.json"

        val stream = FileInputStream(file)

        var jsonString = ""
        stream.use {
            val fileChannel = it.channel
            val mappedByteBuffer = fileChannel.map(
                FileChannel.MapMode.READ_ONLY,
                0,
                fileChannel.size()
            )
            jsonString = Charset.defaultCharset().decode(mappedByteBuffer).toString()
        }
        return jsonString

    }
}