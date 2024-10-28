import com.example.currencyconverterapp.model.ExchangeRatesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiService {
    @GET("latest")
    suspend fun getExchangeRates(
        @Query("base") base: String,
        @Query("apikey") apiKey: String
    ): Response<ExchangeRatesResponse>
}
