package com.zayar.mycomposeapp.wellnessday

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zayar.mycomposeapp.R
import com.zayar.mycomposeapp.models.WellnessDay
import com.zayar.mycomposeapp.ui.theme.WellnessDayTheme

@Composable
fun WellnessDayList(wellnessDays: List<WellnessDay>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
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
    var expended by remember { mutableStateOf(false) }

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .clickable { expended = !expended }
//            .animateContentSize( // Animate size changes when content expands/collapses
//                animationSpec = spring(
//                    dampingRatio = Spring.DampingRatioNoBouncy,
//                    stiffness = Spring.StiffnessMedium
//                )
//            )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Day ${wellnessDay.day}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = stringResource(id = wellnessDay.titleResId),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Box {
                Image(
                    painter = painterResource(id = wellnessDay.imageResId ?: R.drawable.wellness_day1),
                    contentDescription = stringResource(id = wellnessDay.titleResId),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
//                        .padding(bottom = 8.dp)
                )
            }
            AnimatedVisibility(expended) {
                Text(
                    text = stringResource(id = wellnessDay.descriptionResId),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WellnessDayItemPreview() {
    WellnessDayTheme {
        WellnessDayItem(
            wellnessDay = WellnessDay(
                day = 1,
                titleResId = R.string.day1_title,
                descriptionResId = R.string.day1_description,
                imageResId = R.drawable.wellness_day1
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
}
