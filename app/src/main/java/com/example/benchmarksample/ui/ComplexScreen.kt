package com.example.benchmarksample.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.dp
import com.example.benchmarksample.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ComplexScreen(component2List: List<Int>) {
    val lazyListState = rememberLazyListState()

    LazyColumn(state = lazyListState, modifier = Modifier.semantics { testTagsAsResourceId = true }.testTag("container")) {
        item {
            Component1()
        }
        items(component2List) {
            Component2(value = it)
        }
        item {
            Text(text = "Another Section", modifier = Modifier.padding(start = 16.dp))
        }
        items(10) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = null,
                        modifier = Modifier
                            .size(90.dp)
                            .padding(end = 16.dp)
                    )
                    Text(text = "Component3 $it")
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ComplexScreenV2(component2List: List<Int>) {
    val lazyListState = rememberLazyListState()
    Log.v("Tag", lazyListState.firstVisibleItemIndex.toString())

    LazyColumn(state = lazyListState, modifier = Modifier.semantics { testTagsAsResourceId = true }.testTag("container")) {
        item {
            Component1()
        }
        items(component2List) {
            Component2(value = it, component2List)
        }
        item {
            Text(text = "Another Section", modifier = Modifier.padding(start = 16.dp))
        }
        items(10) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = null,
                        modifier = Modifier
                            .size(90.dp)
                            .padding(end = 16.dp)
                    )
                    Text(text = "Component3 $it")
                }
            }
        }
    }
}


@Composable
fun Component1() {
    Row(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            Modifier
                .height(100.dp)
                .width(72.dp)
                .background(color = Color.Cyan)
        )
        Box(
            Modifier
                .height(100.dp)
                .width(72.dp)
                .background(color = Color.LightGray)
        )
        Box(
            Modifier
                .height(100.dp)
                .width(72.dp)
                .background(color = Color.Red)
        )
        Box(
            Modifier
                .height(100.dp)
                .width(72.dp)
                .background(color = Color.Magenta)
        )
    }
}


@Composable
fun Component2(value: Int) {
    Column {
        Text(text = "Header $value")
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(8) {
                Column {
                    Box(
                        Modifier
                            .width(200.dp)
                            .height(100.dp)
                            .background(color = Color.LightGray)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = null
                        )
                    }
                    Text(text = "Item $it")
                }
            }
        }
    }
}

@Composable
fun Component2(value: Int, component2List: List<Int>) {
    Column {
        Text(text = "Header $value")
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(component2List) {
                Column {
                    Box(
                        Modifier
                            .width(200.dp)
                            .height(100.dp)
                            .background(color = Color.LightGray)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = null
                        )
                    }
                    Text(text = "Item $it")
                }
            }
        }
    }
}