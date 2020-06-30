package axel.legue.numbers.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import axel.legue.numbers.R
import axel.legue.numbers.viewmodel.FactNumberViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent

class MainActivity : AppCompatActivity(), KoinComponent {

    private val numberViewModel: FactNumberViewModel by viewModel()
    private lateinit var inputText: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        input_trivia.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                // Do Nothing
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
                // Do Nothing
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                inputText = s.toString()
                Log.i("MainActivity", inputText)
            }
        })

        search_button.setOnClickListener {
            numberViewModel.getFactNumber(inputText).observe(this, Observer { factNumber ->
                result_trivia.text = factNumber?.text
            })
        }

        random_button.setOnClickListener {
            numberViewModel.getRandomNumber().observe(this, Observer { factNumber ->
                result_trivia.text = factNumber?.text
            })
        }

    }

}