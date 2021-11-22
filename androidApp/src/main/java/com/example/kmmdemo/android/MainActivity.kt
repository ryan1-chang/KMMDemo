package com.example.kmmdemo.android

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kmmdemo.Greeting
import android.widget.TextView
import com.example.kmmdemo.WeatherApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.text.method.ScrollingMovementMethod
import android.app.ProgressDialog


fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.movementMethod = ScrollingMovementMethod()
        tv.setTypeface(null, Typeface.BOLD)
        tv.text = greet()

        val dialog = ProgressDialog(this@MainActivity)
        dialog.setMessage("Loading...")
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        dialog.setCancelable(false)

        CoroutineScope(Dispatchers.Main).launch {
            dialog.show()
            val result = withContext(Dispatchers.IO) { WeatherApi().fetchData() }
            dialog.dismiss()
            tv.text = result ?: "No data to display"
        }
    }
}
