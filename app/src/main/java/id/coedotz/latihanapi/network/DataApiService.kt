package id.coedotz.latihanapi.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import id.coedotz.latihanapi.model.Data
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://latihan-api-coedotz.000webhostapp.com/"

private val moshi =
    Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface DataApiService {
    @GET("data-api.php")
    suspend fun getData(): List<Data>
}

object DataApi {
    val service: DataApiService by lazy {
        retrofit.create(DataApiService::class.java)
    }

    enum class ApiStatus { LOADING, SUCCESS, FAILED }
}