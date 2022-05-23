package com.example.retofirebase

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retofirebase.databinding.ItemMovieBinding
import org.json.JSONArray
import org.json.JSONObject


class MainAdapter(private val movies: JSONArray): RecyclerView.Adapter<MainAdapter.MainHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        if(movies[position].toString() != "null"){
            holder.render(movies[position] as JSONObject)
        }

    }

    override fun getItemCount(): Int = movies.length()

    class MainHolder(val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root){
        fun render(movie: JSONObject){
            binding.tvId.setText(movie.getString("id"))
            binding.tvTitle.setText(movie.getString("title"))
            binding.tvYear.setText(movie.getString("year"))
            binding.tvimdbID.setText(movie.getString("imdbID"))
            binding.tvType.setText(movie.getString("type"))
            binding.tvPoster.setText(movie.getString("poster"))
            binding.tvCountry.setText(movie.getString("country"))
            binding.tvGenre.setText(movie.getString("genre"))

        }
    }

}