/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower.plantdetail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.data.Plant
import com.google.samples.apps.sunflower.viewmodels.PlantDetailViewModel

@Composable
fun PlantName(name: String) {
//    <TextView-->
//        android:id="@+id/plant_detail_name"
//        android:layout_width="0dp"
//        android:layout_height="wrap_content"
//        android:layout_marginStart="@dimen/margin_small"
//        android:layout_marginEnd="@dimen/margin_small"
//        android:gravity="center_horizontal"
//        android:text="@{viewModel.plant.name}"
//        android:textAppearance="?attr/textAppearanceHeadline5"
//        app:layout_constraintEnd_toEndOf="parent"
//        app:layout_constraintStart_toStartOf="parent"
//        app:layout_constraintTop_toTopOf="parent"
//        tools:text="Apple" />
    Text(
        text = name,
        style = MaterialTheme.typography.h5, // android:textAppearance="?attr/textAppearanceHeadline5
        modifier = Modifier
            // android:layout_width="match_parent"
            .fillMaxWidth()
            // android:layout_marginStart="@dimen/margin_small"
            // android:layout_marginEnd="@dimen/margin_small"
            .padding(
                horizontal = dimensionResource(id = R.dimen.margin_small)
            )
            // android:gravity="center_horizontal"
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
fun PlantDetailContent(plant: Plant) {
    PlantName(name = plant.name)
}

@Composable
fun PlantDetailDescription(plantDetailViewModel: PlantDetailViewModel) {
    val plant by plantDetailViewModel.plant.observeAsState()
    Surface {
        plant?.let {
            PlantDetailContent(plant = it)
        }
    }
}

@Composable
@Preview
fun PlantNamePreview() {
    PlantName("Apple")
}


@Composable
@Preview
fun PlantDetailContentPreview() {
    val plant = Plant(
        plantId = "id",
        name = "Apple",
        description = "description",
        growZoneNumber = 3,
        wateringInterval = 30,
        imageUrl = ""
    )
    PlantDetailContent(plant = plant)
}