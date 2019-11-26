package com.sarwoedhi.gitapp.data.models

data class SearchResponse(
    var total_count: Int,
    val incomplete_results: Boolean,
    val items: ArrayList<SearchEntity>
)