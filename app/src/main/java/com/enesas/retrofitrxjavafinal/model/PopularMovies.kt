package com.enesas.movieapp.model

import com.google.gson.annotations.SerializedName

data class PopularMovies(
    val page: Int,
    val results: List<ResultPopularMovies>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

