package com.enesas.retrofitrxjavafinal.api

import com.enesas.retrofitrxjavafinal.model.Besin
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface BesinAPI {

    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    fun getSingleBesin() : Single<List<Besin>>

    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    fun getObservableBesin() : Observable<List<Besin>>



    /*
    RxJava is a JVM library that uses observable sequences to perform asynchronous and event-based programming.
    Its primary building blocks are triple O’s, which stand for Operator, Observer, and Observables. And we use them to complete asynchronous tasks in our project.
    It greatly simplifies multithreading in our project. It assists us in determining which thread we want to run the task on.

     */


}