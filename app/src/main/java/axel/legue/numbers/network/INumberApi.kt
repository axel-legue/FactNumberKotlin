package axel.legue.numbers.network

import axel.legue.numbers.model.FactNumber
import retrofit2.http.GET
import retrofit2.http.Path

interface INumberApi {

    @GET("/{number}?json#")
    suspend fun getFactNumber(@Path("number") number: Long): FactNumber

    @GET("random/trivia?json#")
    suspend fun getRandomFactNumber(): FactNumber
}