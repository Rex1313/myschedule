package sszymanski.co.uk.myschedule.dependencyinjection

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import sszymanski.co.uk.myschedule.Values
import sszymanski.co.uk.myschedule.service.EndpointInterface
import javax.inject.Singleton

/**
 * Created by rex on 27/05/2018.
 */
@Module
class DataModule() {


    @Provides
    @Singleton
    fun provideEndpointInterface(retrofit: Retrofit): EndpointInterface{
        return retrofit.create(EndpointInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Values.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

}