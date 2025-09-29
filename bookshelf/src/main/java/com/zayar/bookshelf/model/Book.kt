package com.zayar.bookshelf.model


import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("kind")
    val kind: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("etag")
    val etag: String?,
    @SerializedName("selfLink")
    val selfLink: String?,
    @SerializedName("volumeInfo")
    val volumeInfo: VolumeInfo?,
    @SerializedName("saleInfo")
    val saleInfo: SaleInfo?,
    @SerializedName("accessInfo")
    val accessInfo: AccessInfo?,
    @SerializedName("searchInfo")
    val searchInfo: SearchInfo?
)

val dummyBook = Book(
    kind = "kind",
    id = "id",
    etag = "etag",
    selfLink = "selfLink",
    volumeInfo = VolumeInfo(
        title = "Title",
        authors = listOf("Author 1", "Author 2"),
        publisher = "Publisher",
        publishedDate = "2023-01-01",
        description = "Description",
        industryIdentifiers = listOf(
            IndustryIdentifier(
                type = "type",
                identifier = "identifier"
            ),
            IndustryIdentifier(
                type = "type",
                identifier = "identifier"
            )
        ),
        readingModes = ReadingModes(
            text = true,
            image = true
        ),
        pageCount = 200,
        printType = "printType",
        categories = listOf("category1", "category2"),
        maturityRating = "maturityRating",
        allowAnonLogging = true,
        contentVersion = "contentVersion",
        panelizationSummary = PanelizationSummary(
            containsEpubBubbles = true,
            containsImageBubbles = true
        ),
        imageLinks = ImageLinks(
            smallThumbnail = "smallThumbnail",
            thumbnail = "thumbnail"
        ),
        language = "language",
        previewLink = "previewLink",
        infoLink = "infoLink",
        canonicalVolumeLink = "canonicalVolumeLink"
    ),
    saleInfo = null,
    accessInfo = null,
    searchInfo = null,
)