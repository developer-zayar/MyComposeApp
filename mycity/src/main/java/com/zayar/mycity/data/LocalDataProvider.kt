package com.zayar.mycity.data

import com.zayar.mycity.model.Category
import com.zayar.mycity.model.PlaceDetails
import com.zayar.mycity.model.Recommendation

object LocalDataProvider {
    val categories = listOf(
        Category(1, "Cafés", "https://picsum.photos/200/200?cafe"),
        Category(2, "Restaurants", "https://picsum.photos/200/200?restaurant"),
        Category(3, "Parks", "https://picsum.photos/200/200?park"),
        Category(4, "Museums", "https://picsum.photos/200/200?museum")
    )

    val recommendations = listOf(
        Recommendation(1, 1, "Blue Bottle Coffee", "https://picsum.photos/200/200?coffee"),
        Recommendation(2, 1, "Central Perk", "https://picsum.photos/200/200?cafe"),
        Recommendation(3, 2, "Golden Dragon", "https://picsum.photos/200/200?chinese"),
        Recommendation(4, 2, "The Italian Table", "https://picsum.photos/200/200?italian"),
        Recommendation(5, 3, "Greenwood Park", "https://picsum.photos/200/200?greenpark"),
        Recommendation(6, 3, "Riverside Walk", "https://picsum.photos/200/200?river"),
        Recommendation(7, 4, "City Art Museum", "https://picsum.photos/200/200?art"),
        Recommendation(8, 4, "History & Heritage Center", "https://picsum.photos/200/200?history")
    )

    val placeDetails = listOf(
        PlaceDetails(
            recommendationId = 1,
            title = "Blue Bottle Coffee",
            description = "Famous for single-origin coffee and donuts. Try their ginger scones! Outdoor dog-friendly patio available.",
            imageUrl = "https://picsum.photos/300/200?coffee"
        ),
        PlaceDetails(
            recommendationId = 2,
            title = "Sunset Bakery",
            description = "Delicious pastries and freshly baked bread. A perfect spot for your morning coffee.",
            imageUrl = "https://picsum.photos/300/200?bakery"
        ),
        PlaceDetails(
            recommendationId = 3,
            title = "Golden Dragon",
            description = "Authentic Chinese cuisine with modern vibes. Great dim sum and Peking duck.",
            imageUrl = "https://picsum.photos/300/200?restaurant"
        ),
        PlaceDetails(
            recommendationId = 4,
            title = "Bella Italia",
            description = "Cozy Italian restaurant serving handmade pasta and wood-fired pizza.",
            imageUrl = "https://picsum.photos/300/200?italian"
        ),
        PlaceDetails(
            recommendationId = 5,
            title = "Greenwood Park",
            description = "Beautiful urban park with a jogging track, children’s playground, and plenty of picnic spots.",
            imageUrl = "https://picsum.photos/300/200?park"
        ),
        PlaceDetails(
            recommendationId = 6,
            title = "Ocean View Beach",
            description = "Relaxing beach with white sand and clear water. Perfect for sunsets and picnics.",
            imageUrl = "https://picsum.photos/300/200?beach"
        ),
        PlaceDetails(
            recommendationId = 7,
            title = "City Art Museum",
            description = "Features classical and modern artworks. Current exhibition: Impressionist Landscapes.",
            imageUrl = "https://picsum.photos/300/200?museum"
        ),
        PlaceDetails(
            recommendationId = 8,
            title = "Skyline Rooftop Bar",
            description = "Trendy rooftop bar with panoramic city views, cocktails, and live music on weekends.",
            imageUrl = "https://picsum.photos/300/200?bar"
        )
    )


}