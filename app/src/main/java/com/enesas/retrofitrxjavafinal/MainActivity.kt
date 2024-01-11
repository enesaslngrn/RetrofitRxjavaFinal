package com.enesas.retrofitrxjavafinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.enesas.movieapp.model.DiscoverMovies
import com.enesas.movieapp.model.PopularMovies
import com.enesas.movieapp.model.PopularPeople
import com.enesas.movieapp.service.MovieAPIService
import com.enesas.retrofitrxjavafinal.databinding.ActivityMainBinding
import com.enesas.retrofitrxjavafinal.model.Besin
import com.enesas.retrofitrxjavafinal.api.BesinAPIServis
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Function3
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val besinApiServis = BesinAPIServis()
    private val movieApiServis = MovieAPIService
    private val disposable = CompositeDisposable() // Ne demek bu disposable? Kullan at bunlar. 1 kere kullan işin bitince at gitsin demek. Rxjava3 kütüphanesinden geliyor.

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        var view = binding.root
        setContentView(view)


        // ********************************** BU KISIM ÖĞRENMEK İÇİN **********************************
        /*
        Single.just("Hello")
            .subscribe(
                {
                    println("onSuccess: $it")
                },
                {
                    println("onError: $it")
                }
            )





        Observable.just("Hello","World")
            .subscribe {
                println(it)
            }


        Observable.just("Apple","Orange","Banana") // Single'dan farklı sürekli response almamız gerekirse.
            .subscribe(
                {
                    println("onNext: $it")
                },
                {
                    println("onError: $it")
                },
                {
                    println("onComplete")
                }
            )

        Flowable.just("Apple","Orange","Banana") // Observable'a göre backpressure'a yani fazla veriye daha dayanıklı.
            .subscribe(
                {
                    println("onNext: $it")
                },
                {
                    println("onError: $it")
                },
                {
                    println("onComplete")
                }
            )



        Observable.just("Apple","Orange","Banana")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {map -> map + "2"

            }
            .subscribe{
                println("Map: $it")
            }

        Observable.just("Apple","Orange","Banana")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                Observable.just(it + "2")
                    .subscribeOn(Schedulers.io())
            }
            .subscribe {
                println("flatMap: $it")
            }



        Observable.zip(
            Observable.just("Elma","Portakal","Armut")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()),
            Observable.just("Kırmızı","Turuncu","Yeşil","Sarı")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()),
            { fruit, color ->
                "$fruit $color"
            }
        ).subscribe{
            println("Zip: $it")
        }

        val test1 = Observable.just("Apple", "Orange", "Banana")
        val test2 = Observable.just("Microsoft", "Google")
        val test3 = Observable.just("Grass", "Tree", "Flower", "Sunflower")

        Observable.concat(test1, test2, test3)
            .subscribe{
                println("Concat: $it")
            }



        Observable.merge(
            Observable.interval(1000, TimeUnit.MILLISECONDS).map {"Apple"

            },
            Observable.interval(250, TimeUnit.MILLISECONDS).map {"Orange"

            })
            .take(10)
            .subscribe{
                println("Merge: $it")
            }

         */

        //*****************************************************************************
        /*

        disposable.add( // Aynı fragment içinde belki 10 tane 20 tane istek yapacağız ileride o yüzden hep bu disposable'lar ile istekleri yapacağız.
            besinApiServis.getSingleData() // şimdi burada belirtmek gerek 3 tane şey var. SubscribeOn, ObserveOn ve SubscribeWith
                .subscribeOn(Schedulers.newThread()) // bunun sayesinde oluşturduğumuz Single<T> observable'ına kayıt oluyoruz. Bunu asenkron şekilde arka planda yapmak için newThread açıyoruz.
                .observeOn(AndroidSchedulers.mainThread()) // Kullanıcının gördüğü ui kısmında gözlemleme yapacağız. O yüzden mainThread.
                .subscribeWith(object : DisposableSingleObserver<List<Besin>>(){ // DisposableSingleObserver abstract class olduğu için object : olarak kullanmamız gerekli.
                    override fun onSuccess(t: List<Besin>) {
                        println("Single: $t")
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })
        )

        disposable.add(
            besinApiServis.getObservableData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<Besin>>(){
                    override fun onNext(t: List<Besin>) {
                        println("Observable: $t")
                    }

                    override fun onError(e: Throwable) {
                        println(e)
                    }

                    override fun onComplete() {
                        println("onComplete")
                    }
                })
        )

        disposable.add(
            movieApiServis.getPopularMovieData(1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<PopularMovies>(){
                    override fun onNext(t: PopularMovies) {
                        println(t)
                    }

                    override fun onError(e: Throwable) {
                        println(e)
                    }

                    override fun onComplete() {
                        println("onComplete")
                    }
                })
        )

         */

        disposable.add(
            Observable.zip(
                movieApiServis.getPopularMovieData(1)
                    .subscribeOn(Schedulers.io()),
                movieApiServis.getDiscoverMovieData(1)
                    .subscribeOn(Schedulers.io()),
                movieApiServis.getPopularPeopleData(1)
                    .subscribeOn(Schedulers.io())
            ) { popularmovies, discovermovies, popularpeople ->
                mutableListOf(popularmovies, discovermovies, popularpeople)
            }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {result->
                        val popularMovies = result[0] as PopularMovies
                        val discoverMovies = result[1] as DiscoverMovies
                        val popularPeople = result[2] as PopularPeople

                        println("Popular Movies: $popularMovies")
                        println("Discover Movies: $discoverMovies")
                        println("Popular People: $popularPeople")
                    },
                    {
                        it.printStackTrace()
                    },
                    {
                        println("onComplete")
                    }
                )
        )
    }
}