package com.example.mysearching

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var searchBar: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        searchBar = findViewById(R.id.search_bar)


        searchBar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                lifecycleScope.launch {

                    searchViewModel.search(s.toString())
                }
            }
        })


        lifecycleScope.launch {
            searchViewModel.searchFlow.collect { query ->
                val filteredNames = searchViewModel.filterNames(query)
                Toast.makeText(this@MainActivity, filteredNames.joinToString(), Toast.LENGTH_SHORT).show()
            }
    }
}}
