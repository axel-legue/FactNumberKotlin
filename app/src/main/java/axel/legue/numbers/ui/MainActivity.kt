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
    companion object {
        private var KEY_USER_INPUT: String = "user_input"
        private var KEY_RESULT: String = "result"
    }

    private val numberViewModel: FactNumberViewModel by viewModel()
    private var inputText: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.containsKey(KEY_USER_INPUT)) {
            inputText = savedInstanceState.getString(KEY_USER_INPUT) ?: ""
            input_trivia.text = Editable.Factory.getInstance().newEditable(inputText)
        }
        if (savedInstanceState.containsKey(KEY_RESULT)) {
            result_trivia.text = savedInstanceState.getString(KEY_RESULT) ?: ""
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (inputText.isNotEmpty()) {
            outState.putString(KEY_USER_INPUT, inputText)
        }
        if (result_trivia.text.isNotEmpty()) {
            outState.putString(KEY_RESULT, result_trivia.text.toString())
        }
    }

    override fun onStart() {
        super.onStart()

        input_trivia.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) { // Do Nothing
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) { // Do Nothing
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
            input_trivia.text = null
            input_trivia.clearFocus()
            numberViewModel.getRandomNumber().observe(this, Observer { factNumber ->
                result_trivia.text = factNumber?.text
            })
        }
    }
}