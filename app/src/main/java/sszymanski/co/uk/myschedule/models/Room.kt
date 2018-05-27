package sszymanski.co.uk.myschedule.models

import com.google.gson.annotations.SerializedName

/**
 * Created by rex on 27/05/2018.
 */
data class Room(
        @SerializedName("_id") val id: Int,
        @SerializedName("room_name") val roomName: String,
        @SerializedName("schedule") val schedule: Int,
        @SerializedName("big_cycle") val bigCycle: Int,
        @SerializedName("room_id") val roomId: Int) {
}