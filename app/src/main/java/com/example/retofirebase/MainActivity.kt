package com.example.retofirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retofirebase.databinding.ActivityMainBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = Firebase.database
        val myRef = database.reference

        binding.btnGuardar.setOnClickListener{
            val Id = binding.etId.text.toString()
            val Title = binding.etTitle.text.toString()
            val Year = binding.etYear.text.toString()
            val imdbID = binding.etimdbID.text.toString()
            val Type = binding.etType.text.toString()
            val Poster = binding.etPoster.text.toString()
            val Country = binding.etCountry.text.toString()
            val Genre = binding.etGenre.text.toString()
            myRef.child("Movies").child(Id).setValue(Movies(
                Id, Title, Year, imdbID, Type, Poster, Country, Genre))

            myRef.child("Movies").get().addOnSuccessListener { response ->
                val resmap = response.value as ArrayList<Map <String, String>>
                Log.d("firebaseResponse", resmap.toString())
                resmap.forEach{ movie ->
                    if(!movie.isNullOrEmpty()){
                        Log.d("firebaseResponse", "titulo : ${movie["title"]}")
                    }
                    val jsonarray = JSONArray(resmap)
                    binding.rvMoviesEntries.adapter = MainAdapter(jsonarray)
                }}.addOnFailureListener{
                Log.e("firebaseResponse", "Error getting data", it)
            }
            }

    }
}