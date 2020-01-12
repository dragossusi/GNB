package ro.dragossusi.api.gnb.modules

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

@Module
class RetrofitModule {

    @Provides
    fun okHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        return client
    }

    @Provides
    fun moshi() = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter())
        .build()

    @Provides
    fun retrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(HOST)
            .build()
    }

}

private const val HOST = "http://gnb.dev.airtouchmedia.com/"