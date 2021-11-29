package com.example.kmmdemo.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kmmdemo.WeatherApi
import com.example.kmmdemo.android.ui.theme.KMMDemoTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
    }
}

fun Color.fromHex(colorString: String) = Color(android.graphics.Color.parseColor(colorString))
val String.color
    get() = Color(android.graphics.Color.parseColor(this))

@Preview
@Composable
fun MainContent() {
    KMMDemoTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "KMMDemo")
                    },
                    backgroundColor = "#1589FF".color,
                )
            },
            content = {
                Box(
                    Modifier
                        .background(Color(0).fromHex("#BCC6CC"))
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    var data by remember { mutableStateOf("") }
                    CoroutineScope(Dispatchers.Main).launch {
                        data = withContext(Dispatchers.IO) { WeatherApi().fetchData() ?: "" }
                    }
                    if (data.isNotEmpty()) {
                        val weatherData = WeatherApi().convertJson(data)
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState()),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            val location = weatherData.records.locations[0].location[0]
                            val weather = location.weatherElement[0].time
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = location.locationName,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                            weather.forEach { value ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp),
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(text = "${value.startTime} ~ ${value.endTime}")
                                        Text(text = value.elementValue[0].value)
                                    }
                                }
                            }
                        }
                    } else {
                        Text(
                            "No Data yet",
                            color = Color.Red,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }
        )
    }
}