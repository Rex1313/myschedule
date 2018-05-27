package sszymanski.co.uk.myschedule.models

import com.google.gson.annotations.SerializedName

/**
 * Created by rex on 27/05/2018.
 */
data class Person(
    @SerializedName("_id") val id:Int,
    @SerializedName("person_name") val personName:String,
    @SerializedName("person_id") val personId:Int,
    @SerializedName("order_no") val orderNo:Int,
    @SerializedName("type") val type:String,
    @SerializedName("is_excluded") val isExcluded:Int) {


    override fun toString(): String {
        return personName
    }
}