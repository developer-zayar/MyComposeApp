package com.zayar.mycomposeapp.data.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.zayar.mycomposeapp.R

class ArtWork(
    val id: Int,
    val title: String,
    val artist: String,
    val year: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val descriptionResourceId: Int,
    val contentDescription: String
) {
    override fun toString(): String {
        return "ArtWork(id=$id, title='$title', artist='$artist', imageResourceId=$imageResourceId, descriptionResourceId=$descriptionResourceId)"
    }

}

val artWorks = listOf(
    ArtWork(
        1,
        "Starry Night",
        "Vincent van Gogh",
        1889,
        R.drawable.starry_night,
        R.string.starry_night_desc,
        "Starry Night painting"
    ),
    ArtWork(
        2,
        "Mona Lisa",
        "Leonardo da Vinci",
        1503,
        R.drawable.mona_lisa_by_leonardo_da_vinci,
        R.string.mona_lisa_desc,
        "Mona Lisa painting"
    ),
    ArtWork(
        3,
        "The Scream",
        "Edvard Munch",
        1893,
        R.drawable.the_scream,
        R.string.the_scream_desc,
        "The Scream painting"
    ),
    ArtWork(
        4,
        "The Persistence of Memory",
        "Salvador Dalí",
        1931,
        R.drawable.the_persistence_of_memory,
        R.string.the_persistence_of_memory_desc,
        "The Persistence of Memory painting"
    ),
    ArtWork(
        5,
        "Girl with a Pearl Earring",
        "Johannes Vermeer",
        1665,
        R.drawable.girl_with_a_pearl_earring,
        R.string.girl_with_pearl_desc,
        "Girl with a Pearl Earring painting"
    )
)