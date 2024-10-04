package com.example.mysearching

import android.widget.Toast
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class SearchViewModel:ViewModel() {
    private val _searchFlow = MutableSharedFlow<String>()
    val searchFlow = _searchFlow.asSharedFlow()


    private val names = listOf("mona", "eman", "Mary", "moshera", "emma", "Ethraa")


    suspend fun search(query: String) {
       val s= _searchFlow.emit(query)
    }


    fun filterNames(query: String): List<String> {
        return names.filter { it.startsWith(query, ignoreCase = true) }
    }

//lateinit var list:List<String>
// suspend fun collecting(): List<String>{
//        searchFlow.collect { query ->
//            list= filterNames(query)
//            }
//   return  list
//    }
}