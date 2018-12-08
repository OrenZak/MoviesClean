package clean.movies.main

import clean.movies.main.di.DaggerAppComponent
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.interceptors.HttpLoggingInterceptor
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import okhttp3.OkHttpClient




open class MoviesApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        // Adding an Network Interceptor for Debugging purpose :
        val okHttpClient = OkHttpClient().newBuilder()
                .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        AndroidNetworking.initialize(applicationContext, okHttpClient)
        AndroidNetworking.enableLogging()
    }
}