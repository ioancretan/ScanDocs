package com.example.myapplication.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.scans.ScansViewModel

@ExperimentalFoundationApi
@Composable
fun ScansList(viewModel: ScansViewModel) {
    val state = viewModel.state.value

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Spacer(modifier = Modifier.weight(1.0f))
            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .clickable(onClick = {
                        viewModel.deleteSelectedScans()
                    })
                    .weight(1f), text = "Select",
                textAlign = TextAlign.Center
            )

            if (state.isMAnageButtonVisible.value) {
                Text(
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable(onClick = {
                            viewModel.deleteSelectedScans()
                        })
                        .weight(1f), text = "Merge",
                    textAlign = TextAlign.Center
                )
            } else {
                Spacer(modifier = Modifier.weight(1.0f))
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = 12.dp,
                top = 16.dp,
                end = 12.dp,
                bottom = 16.dp
            ),
            content = {
                items(state.scans.value.size, key = { state.scans.value[it].id }) { index ->

                    val bgColor: Color by animateColorAsState(
                        if (state.scans.value[index].isSelected.value) Color.Gray else Color.LightGray,
                        animationSpec = tween(500, easing = LinearEasing)
                    )

                    Card(
                        backgroundColor = bgColor,
                        modifier = Modifier
                            .padding(4.dp)
                            .width(100.dp)
                            .height(150.dp)
                            .clickable(onClick = {
                                viewModel.selectScan(state.scans.value[index])
                            })
                            .animateItemPlacement(animationSpec = tween(durationMillis = 1000)),
                        elevation = 8.dp,
                    ) {
                        Text(
                            text = state.scans.value[index].id.toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            color = Color(0xFFFFFFFF),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(16.dp)
                        )
                    }
                }
            }
        )
    }
}