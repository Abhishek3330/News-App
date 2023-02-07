package test.com.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import test.com.newsly.Article
import test.com.newsly.NewsDetailsPage
import test.com.newsly.R

/**
 * Created by Mukesh on 17-12-2022.
 */
class NewsAdapter(val context: Context,val articles: List<Article>) : RecyclerView.Adapter<NewsAdapter.articleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): articleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)
        return articleViewHolder(view)
    }

    override fun onBindViewHolder(holder: articleViewHolder, position: Int) {
        val article = articles[position]
        holder.newsTitle.text = article.title
        holder.newsDescription.text = article.description
        // load Image
        Glide.with(context).load(article.urlToImage).into(holder.newsImage)

        // click listener on item view
        holder.itemView.setOnClickListener {
            val intent = Intent(context,NewsDetailsPage::class.java)
            intent.putExtra("URL",article.url)
            context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class articleViewHolder(itemView: View):ViewHolder(itemView){
        var newsImage = itemView.findViewById<ImageView>(R.id.newsImage)
        var newsTitle = itemView.findViewById<TextView>(R.id.newsTitle)
        var newsDescription = itemView.findViewById<TextView>(R.id.newsDescription)


    }
}