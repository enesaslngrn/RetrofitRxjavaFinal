package com.enesas.movieapp.service

import com.enesas.movieapp.model.DiscoverMovies
import com.enesas.movieapp.model.PopularMovies
import com.enesas.movieapp.model.PopularPeople
import com.enesas.retrofitrxjavafinal.util.Constants.Companion.API_KEY
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object MovieAPIService {

    private val BASE_URL = "https://api.themoviedb.org/3/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(MovieAPI::class.java)

    fun getPopularMovieData(page : Int) : Observable<PopularMovies>{
        return api.getPopularMovies(API_KEY,page)
    }

    fun getDiscoverMovieData(page : Int) : Observable<DiscoverMovies>{
        return api.getDiscoverMovies(API_KEY,page)
    }

    fun getPopularPeopleData(page : Int) : Observable<PopularPeople>{
        return api.getPopularPeople(API_KEY,page)
    }


}