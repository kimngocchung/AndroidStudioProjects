import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.currencyfreaks.com/") // Đổi URL cho phù hợp với API bạn chọn
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: CurrencyApiService by lazy {
        retrofit.create(CurrencyApiService::class.java)
    }
}

