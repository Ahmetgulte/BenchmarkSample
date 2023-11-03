package com.example.benchmarksample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.benchmarksample.ui.ComplexScreen
import com.example.benchmarksample.ui.theme.BenchmarkSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BenchmarkSampleTheme {
                val componentList = (1..8).toList()
                ComplexScreen(component2List = componentList)
            }
        }
    }
}