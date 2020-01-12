package ro.dragossusi.api.gnb.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RateResponse(

    @Json(name = "rate")
    val rate: String,

    @Json(name = "from")
    val from: String,

    @Json(name = "to")
    val to: String
)