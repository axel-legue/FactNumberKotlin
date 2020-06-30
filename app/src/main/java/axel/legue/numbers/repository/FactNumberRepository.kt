package axel.legue.numbers.repository

import axel.legue.numbers.model.FactNumber
import axel.legue.numbers.network.RetrofitService
import org.koin.core.KoinComponent
import org.koin.core.inject


class FactNumberRepository : KoinComponent {

    private val retrofitService by inject<RetrofitService>()

    suspend fun getRandomNumber(): FactNumber {
       return retrofitService.getNumberService().getRandomFactNumber()
    }

    suspend fun getFactNumber(number: Long): FactNumber {
        return retrofitService.getNumberService().getFactNumber(number)
    }

}
