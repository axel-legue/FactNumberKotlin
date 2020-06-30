package axel.legue.numbers.model

import com.google.gson.annotations.SerializedName


data class FactNumber(
    val found: Boolean,
    val number: String,
    val text: String,
    val type: String
)
