package com.enesas.movieapp.model


import com.google.gson.annotations.SerializedName



data class ResultPopularPeople(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("gender")
    val gender: Int?,
    @SerializedName("biography")
    var biography: String?,
    @SerializedName("birthday")
    var birthday: String?,
    @SerializedName("place_of_birth")
    var placeOfBirth: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("original_name")
    val originalName: String?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("profile_path")
    val profilePath: String?
)