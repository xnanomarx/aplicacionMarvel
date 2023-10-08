package com.example.intentosraros.network

import com.squareup.moshi.Json

data class Serie (
    @Json(name="title")
    val title: String,
    @Json(name="startYear")
    val startYear: Int,
    @Json(name="endYear")
    val endYear: Int
)

data class SerieResponse(
    val data: SeriesDataContainer
)

data class SeriesDataContainer(
    val results: List<Serie>
)
