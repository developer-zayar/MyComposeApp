package com.zayar.mycomposeapp.wellnessday

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zayar.mycomposeapp.models.WellnessDay

@Composable
fun WellnessDayList(wellnessDays: List<WellnessDay>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(wellnessDays) { wellnessDay ->
            WellnessDayItem(
                wellnessDay = wellnessDay,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun WellnessDayItem(wellnessDay: WellnessDay, modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Day ${wellnessDay.day}",
                style = androidx.compose.material3.MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = stringResource(id = wellnessDay.titleResId),
                style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = stringResource(id = wellnessDay.descriptionResId),
                style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}