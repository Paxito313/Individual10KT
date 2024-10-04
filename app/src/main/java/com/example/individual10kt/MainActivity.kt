package com.example.individual10kt

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var etAmount: EditText
    private lateinit var spinnerCurrency: Spinner
    private lateinit var tvResult: TextView
    private lateinit var btnConvert: Button
    private lateinit var btnReset: Button

    private val exchangeRates = mapOf(
        "USD" to 0.0012,
        "EUR" to 0.0011,
        "GBP" to 0.00095,
        "JPY" to 0.18,
        "CNY" to 0.0087,
        "AUD" to 0.0019,
        "CAD" to 0.0016,
        "BRL" to 0.006,
        "INR" to 0.1,
        "MXN" to 0.021
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etAmount = findViewById(R.id.etAmount)
        spinnerCurrency = findViewById(R.id.spinnerCurrency)
        tvResult = findViewById(R.id.tvResult)
        btnConvert = findViewById(R.id.btnConvert)
        btnReset = findViewById(R.id.btnReset)

        val currencies = exchangeRates.keys.toTypedArray()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCurrency.adapter = adapter

        btnConvert.setOnClickListener {
            val amountString = etAmount.text.toString()
            if (amountString.isNotEmpty()) {
                val amount = amountString.toDouble()
                val selectedCurrency = spinnerCurrency.selectedItem.toString()
                val conversionRate = exchangeRates[selectedCurrency]
                if (conversionRate != null) {
                    val result = amount * conversionRate
                    tvResult.text = "Resultado: %.2f %s".format(result, selectedCurrency)
                } else {
                    Toast.makeText(this, "Divisa no disponible", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnReset.setOnClickListener {
            etAmount.text.clear()
            tvResult.text = "Resultado: "
            spinnerCurrency.setSelection(0)
        }
    }
}
