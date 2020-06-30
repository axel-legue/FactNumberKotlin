package axel.legue.numbers.injection

import axel.legue.numbers.network.RetrofitService
import axel.legue.numbers.repository.FactNumberRepository
import axel.legue.numbers.viewmodel.FactNumberViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val appModule = module {

    single { RetrofitService() }

    single{FactNumberRepository()}

    viewModel { FactNumberViewModel(get()) }
}