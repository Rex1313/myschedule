package sszymanski.co.uk.myschedule.models

import com.google.gson.annotations.SerializedName

/**
 * Created by rex on 27/05/2018.
 */
data class CleaningEvent(
        @SerializedName("_id")val id: Int,
    @SerializedName("room_id")val roomId:Int,
    @SerializedName("person_id")val personId:Int,
    @SerializedName("cleaning_date")val cleaningDate:String,
    @SerializedName("cleaning_type")val cleaningType: String,
        @SerializedName("is_passed") val isPassed:Int,
        @SerializedName("notes")val notes:String,
        @SerializedName("person_name") val personName:String,
        @SerializedName("type") val personType:String,
        @SerializedName("is_excluded") val isExcluded:Int,
        @SerializedName("room_name")val roomName:String,
        @SerializedName("avatar") val avatarUrl:String) {
}