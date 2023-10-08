import com.example.intentosraros.network.SerieResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object ApiClient {

    private const val BASE_URL = "https://gateway.marvel.com"

    // Reemplaza con tus propias claves de autenticación proporcionadas por Marvel
    private const val API_PUBLIC_KEY = "a6e2c24e6467798428b1294b52783ed3"
    //private const val API_PRIVATE_KEY = "d803d1c72a574d92ff74a06f9fb2bd1ff8df76fe"
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val interceptor = Interceptor { chain ->
        val timeStamp = "1"
        val hash = "72ef2ae6268c9a05c66b67ba7b1fae36"
        val originalRequest: Request = chain.request()
        val originalHttpUrl = originalRequest.url()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("ts", timeStamp)
            .addQueryParameter("apikey", API_PUBLIC_KEY)
            .addQueryParameter("hash", hash)
            .build()

        val requestBuilder: Request.Builder = originalRequest.newBuilder()
            .url(url)

        val request: Request = requestBuilder.build()
        chain.proceed(request)
    }

    private val httpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()


    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

interface ApiService {
    @GET("/v1/public/series")
    fun fetchSeries(@Query("limit") limit: Int): Call<SerieResponse>
}

