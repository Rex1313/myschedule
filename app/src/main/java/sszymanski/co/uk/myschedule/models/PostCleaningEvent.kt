package sszymanski.co.uk.myschedule.models

import com.google.gson.annotations.SerializedName

/**
 * Created by rex on 27/05/2018.
 */
class PostCleaningEvent(
        @SerializedName("room_id") var roomId: Int=0,
        @SerializedName("person_id") var personId: Int=0,
        @SerializedName("cleaning_date") var cleaningDate: Long=0,
        @SerializedName("cleaning_type") var cleaningType: String="",
        @SerializedName("notes") var notes: String=""
) {

}