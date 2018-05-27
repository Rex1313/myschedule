package sszymanski.co.uk.myschedule.models

import com.google.gson.annotations.SerializedName

/**
 * Created by rex on 27/05/2018.
 */
class PostCleaningEvent(
        @SerializedName("room_id") val roomId: Int,
        @SerializedName("person_id") val personId: Int,
        @SerializedName("cleaning_date") val cleaningDate: String,
        @SerializedName("cleaning_type") val cleaningType: String,
        @SerializedName("notes") val notes: String
) {

}