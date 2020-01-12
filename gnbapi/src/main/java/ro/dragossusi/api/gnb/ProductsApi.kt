package ro.dragossusi.api.gnb

import dagger.Provides
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import ro.dragossusi.api.gnb.model.ProductResponse

interface ProductsApi {

    @GET("transactions.json")
    suspend fun getProducts(): Response<List<ProductResponse>>

    @dagger.Module
    class Module {
        @Provides
        fun productsApi(retrofit: Retrofit): ProductsApi = retrofit.create()
    }

}