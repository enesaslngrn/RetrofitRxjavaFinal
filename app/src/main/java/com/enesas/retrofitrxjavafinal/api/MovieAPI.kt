package com.enesas.movieapp.service

import com.enesas.movieapp.model.DiscoverMovies
import com.enesas.movieapp.model.PopularMovies
import com.enesas.movieapp.model.PopularPeople
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    // "https://api.themoviedb.org/3/trending/movie/day?language=tr-1974"

    @GET("trending/movie/day?language=tr-1974")
    fun getPopularMovies(@Query("api_key") api_key: String,
                         @Query("page") page: Int) : Observable<PopularMovies>


    @GET("discover/movie?language=tr-1974")
    fun getDiscoverMovies(@Query("api_key") api_key : String,
                          @Query("page") page : Int) : Observable<DiscoverMovies>

    @GET("person/popular?language=tr-1974")
    fun getPopularPeople(@Query("api_key") api_key : String,
                          @Query("page") page : Int) : Observable<PopularPeople>


}