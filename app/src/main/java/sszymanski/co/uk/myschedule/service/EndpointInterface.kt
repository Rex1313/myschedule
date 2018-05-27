package sszymanski.co.uk.myschedule.service

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import sszymanski.co.uk.myschedule.models.CleaningEvent

/**
 * Created by rex on 27/05/2018.
 */
interface EndpointInterface {

    @GET("cleaning")
    fun getCleanings(@Query("from") from:String, @Query("to") to:String):Observable<List<CleaningEvent>>


}