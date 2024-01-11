package com.enesas.movieapp.model


import com.google.gson.annotations.SerializedName

data class PopularPeople(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<ResultPopularPeople?>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)