package ro.dragossusi.api.gnb.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductResponse(

    @Json(name = "amount")
    val amount: String,

    @Json(name = "currency")
    val currency: String,

    @Json(name = "sku")
    val name: String
)