package axel.legue.numbers.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    companion object {
        const val BASE_URL = "http://numbersapi.com/"
    }

    fun getNumberService(): INumberApi{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val errorInterceptor = ErrorInterceptor()
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addNetworkInterceptor(errorInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().serializeNulls().create()
                )
            )
            .build()

        return retrofit.create(INumberApi::class.java)
    }




}

