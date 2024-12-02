import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CurrencyConverterViewModel : ViewModel() {
    private val _exchangeRates = MutableLiveData<Map<String, Double>>()
    val exchangeRates: LiveData<Map<String, Double>> = _exchangeRates

    fun fetchExchangeRates(apiKey: String) {
        viewModelScope.launch {
            val response = RetrofitInstance.api.getExchangeRates("USD", apiKey)
            if (response.isSuccessful) {
                _exchangeRates.value = response.body()?.rates
            }
        }
    }

    fun convertCurrency(amount: Double, fromRate: Double, toRate: Double): Double {
        return (amount / fromRate) * toRate
    }
}
