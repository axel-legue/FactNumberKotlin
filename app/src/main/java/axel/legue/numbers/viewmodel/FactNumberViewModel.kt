package axel.legue.numbers.viewmodel

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import axel.legue.numbers.model.FactNumber
import axel.legue.numbers.repository.FactNumberRepository
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinComponent
import org.koin.core.inject

class FactNumberViewModel(@NonNull app: Application) : AndroidViewModel(app), KoinComponent {

    private val factNumberRepository: FactNumberRepository by inject()

    fun getRandomNumber(): LiveData<FactNumber> = liveData(Dispatchers.IO) {
        emit(factNumberRepository.getRandomNumber())
    }

    fun getFactNumber(number: String): LiveData<FactNumber> = liveData(Dispatchers.IO) {
        emit(factNumberRepository.getFactNumber(number.toLong()))
    }
}