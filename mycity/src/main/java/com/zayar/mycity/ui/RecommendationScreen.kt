package com.zayar.mycity.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.zayar.mycity.R
import com.zayar.mycity.model.Recommendation

@Composable
fun RecommendationScreen(
    recommendationList: List<Recommendation>,
    onItemClicked: (Recommendation) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier
    ) {
        items(items = recommendationList, key = { it.id }) { recommendation ->
            RecommendationListItem(
                recommendation = recommendation,
                onItemClick = onItemClicked
            )
        }
    }
}

@Composable
fun RecommendationListItem(
    recommendation: Recommendation,
    onItemClick: (Recommendation) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick(recommendation) },
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = modifier
        ) {
//            AsyncImage(
//                model = recommendation.imageUrl,
//                contentDescription = recommendation.name,
//                modifier = Modifier
//                    .size(80.dp)
//                    .padding(8.dp)
//            )
            Box {
                Image(
                    painter = painterResource(R.drawable.my_city),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Text(recommendation.name, style = MaterialTheme.typography.titleMedium)
        }
    }
}