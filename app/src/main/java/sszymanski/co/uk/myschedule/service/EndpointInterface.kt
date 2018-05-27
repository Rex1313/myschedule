package sszymanski.co.uk.myschedule.service

import io.reactivex.Observable
import org.json.JSONObject
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import sszymanski.co.uk.myschedule.models.CleaningEvent
import sszymanski.co.uk.myschedule.models.Person
import sszymanski.co.uk.myschedule.models.PostCleaningEvent
import sszymanski.co.uk.myschedule.models.Room

/**
 * Created by rex on 27/05/2018.
 */
interface EndpointInterface {

    @GET("people")
    fun getAllPeople(): Observable<List<Person>>

    @GET("rooms")
    fun getAllRooms(): Observable<List<Room>>

    @GET("cleaning")
    fun getCleanings(@Query("from") from: String, @Query("to") to: String): Observable<List<CleaningEvent>>

    @POST("cleaning/add")
    fun postNewCleaning(@Body cleaningModel: PostCleaningEvent): Observable<JSONObject>

}