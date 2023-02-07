package test.com.newsly

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import test.com.Adapter.NewsAdapter

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: NewsAdapter
    private var article = mutableListOf<Article>() // for degine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView =findViewById(R.id.newsListRv)

        adapter = NewsAdapter(this@MainActivity, article)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        getNews()
    }

    // call the singleton object of retrofit

    private fun getNews() {

        val news = NewsService.newsInstance.getHeadlines("in", 1)

        // All requests of retrofit are put in the queue for one by one process
        // As soon as complete the one request then so his call the callback

        news.enqueue(object : Callback<News> {

            override fun onResponse(call: Call<News>, response: Response<News>) {

                val news = response.body()
                if (news != null) {
                    Log.d("SUCCESS", news.toString())
                    article.addAll(news.articles)

                    adapter.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<News>, t: Throwable) {

                Log.d("FAIL", "Error is fetching News",t)
            }
        })
    }
}
