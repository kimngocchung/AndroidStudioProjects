package com.example.currencyconverterapp

import CurrencyConverterViewModel
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.Spinner
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import android.widget.ArrayAdapter
import com.example.currencyconverterapp.R


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CurrencyConverterViewModel
    private lateinit var etAmount: EditText
    private lateinit var spinnerFrom: Spinner
    private lateinit var spinnerTo: Spinner
    private lateinit var btnConvert: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etAmount = findViewById(R.id.etAmount)
        spinnerFrom = findViewById(R.id.spinnerFrom)
        spinnerTo = findViewById(R.id.spinnerTo)
        btnConvert = findViewById(R.id.btnConvert)
        tvResult = findViewById(R.id.tvResult)

        viewModel = ViewModelProvider(this).get(CurrencyConverterViewModel::class.java)

        viewModel.fetchExchangeRates("7f84fed2ebbf488b80e0fb996927b25f") // Thay "YOUR_API_KEY" bằng API key của bạn

        viewModel.exchangeRates.observe(this, Observer { rates ->
            // Cập nhật các spinner với danh sách tiền tệ từ rates
            val currencies = rates.keys.toList()
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
            spinnerFrom.adapter = adapter
            spinnerTo.adapter = adapter
        })

        btnConvert.setOnClickListener {
            val amount = etAmount.text.toString().toDoubleOrNull()
            if (amount != null) {
                val fromCurrency = spinnerFrom.selectedItem.toString()
                val toCurrency = spinnerTo.selectedItem.toString()
                val fromRate = viewModel.exchangeRates.value?.get(fromCurrency) ?: 1.0
                val toRate = viewModel.exchangeRates.value?.get(toCurrency) ?: 1.0
                val result = viewModel.convertCurrency(amount, fromRate, toRate)
                tvResult.text = "Kết quả: $result $toCurrency"
            } else {
                tvResult.text = "Vui lòng nhập số tiền hợp lệ."
            }
        }
    }
}
