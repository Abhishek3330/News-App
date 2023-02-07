package test.com.newsly

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Mukesh on 17-12-2022.
 */

// https://newsapi.org/v2/everything?q=apple&from=2022-12-15&to=2022-12-15&sortBy=popularity&apiKey=API_KEY
// https://newsapi.org/v2/top-headlines?country=in&apiKey=API_KEY
const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "e8494a79947d474fb7b91cdd024a14f3"

interface NewsInterface {
    @GET(value = "/v2/top-headlines?apiKey=$API_KEY")
    fun getHeadlines(@Query("country") country: String,@Query("page") page:Int):Call<News>

    // how it work this function => when we call the function , function will generate webAddress below like this

    // https://newsapi.org/v2/top-headlines?apiKey=e8494a79947d474fb7b91cdd024a14f3&country=in&page=1
}

//Now we create object of retrofit with Singleton function because we want to create one object only for hole program

object NewsService{
    val newsInstance :NewsInterface
    init {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}