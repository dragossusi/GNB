package ro.dragossusi.api.gnb

import dagger.Module
import dagger.Provides
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import ro.dragossusi.api.gnb.model.RateResponse

interface RatesApi {

    @GET("rates.json")
    suspend fun getRates(): Response<List<RateResponse>>

    @dagger.Module
    class Module {
        @Provides
        fun ratesApi(retrofit: Retrofit): RatesApi = retrofit.create()
    }

}