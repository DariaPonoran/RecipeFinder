package com.example.recipefinder.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.io.IOException

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Favorites"
    }

    private val _searchQuery = MutableLiveData<String>().apply {
        value = ""
    }
    val searchQuery: LiveData<String> = _searchQuery

    private val _searchResult = MutableLiveData<String>().apply {
        value = ""
    }
    val searchResult: LiveData<String> = _searchResult

    private val client = OkHttpClient()

    fun updateSearchQuery(newQuery: String) {
        _searchQuery.value = newQuery
    }


    fun searchRecipe(query: String) {
        val apiKey = System.getenv("OPENAI_API_KEY") ?: ""
        val url = "https://api.openai.com/v1/completions"

        val requestBody = JSONObject()
        requestBody.put("model", "text-davinci-003")
        requestBody.put("prompt", "Suggest a recipe based on the following query: $query")
        requestBody.put("max_tokens", 150)

        val body = RequestBody.create("application/json".toMediaTypeOrNull(), requestBody.toString())

        val request = Request.Builder()
            .url(url)
            .post(body)
            .addHeader("Authorization", "Bearer $apiKey")
            .addHeader("Content-Type", "application/json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                _searchResult.postValue("Failed to fetch data")
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let { responseBody ->
                    val json = JSONObject(responseBody)
                    val result = json.getJSONArray("choices").getJSONObject(0).getString("text")
                    _searchResult.postValue(result.trim())
                }
            }
        })
    }

}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(18.dp))
            .background(Color.White, shape = RoundedCornerShape(18.dp)),
        placeholder = {
            Text(
                "What do you feel like eating?",
                style = MaterialTheme.typography.body2.copy(
                    color = Color.Gray,
                    fontSize = 18.sp
            ))
        },
        trailingIcon = {
            Icon(
                Icons.Filled.Search,
                contentDescription = "Search Icon",
                tint = Color.Gray,
                modifier = Modifier.size(20.dp)
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearch() }),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}
