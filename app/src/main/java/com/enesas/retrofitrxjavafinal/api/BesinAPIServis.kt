package com.enesas.retrofitrxjavafinal.api

import com.enesas.retrofitrxjavafinal.model.Besin
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class BesinAPIServis {

    private val BASE_URL = "https://raw.githubusercontent.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(BesinAPI::class.java)

    fun getSingleData() : Single<List<Besin>>{
        return api.getSingleBesin()
    }

    fun getObservableData() : Observable<List<Besin>> {
        return api.getObservableBesin()
    }

}