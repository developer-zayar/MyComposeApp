package com.zayar.mycity.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.zayar.mycity.R
import com.zayar.mycity.data.LocalDataProvider
import com.zayar.mycity.model.Category
import com.zayar.mycity.ui.theme.MyCityTheme

@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier
    ) {
        items(items = LocalDataProvider.categories, key = { it.id }) { category ->
            CategoryListItem(
                category = category,
                onItemClick = {},
            )
        }
    }
}

@Composable
fun CategoryListItem(
    category: Category,
    onItemClick: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.category_item_height))
            .clickable { onItemClick(category) }
    ) {
        Box(
            modifier = modifier
                .fillMaxHeight()
        ) {
            Image(
                painter = painterResource(R.drawable.my_city),
                contentDescription = null,
                alignment = Alignment.Center,
                contentScale = ContentScale.FillWidth
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .fillMaxWidth()
        ) {
            Text(
                text = category.name,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CategoryListItemPreview() {
    MyCityTheme {
        CategoryListItem(
            category = LocalDataProvider.categories[0],
            onItemClick = {}
        )
    }
}