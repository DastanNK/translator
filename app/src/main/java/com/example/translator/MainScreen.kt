package com.example.translator

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.translator.api.NetworkResult

@Composable
fun MainScreen(viewModel: ViewModel) {
    val result by viewModel.tranData.collectAsState()
    val word = remember { mutableStateOf("") }
    //val wordFromViewModel=viewModel.wordText.collectAsState()
    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = word.value,
            onValueChange = { word.value = it },
            label = { Text("Word") },
            placeholder = { Text("Enter your word") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        viewModel.onWordEnter(word.value)
                    })
            }
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (result) {
                is NetworkResult.Initial -> {
                    Text(text = "Search for a character")
                }

                is NetworkResult.Success<*> -> {
                    Text(text = " ${result}")
                    //ShowCharactersList(result)
                }

                is NetworkResult.Loading<*> -> {
                    //Text(text = "Error: ${result.data?.code}")
                    CircularProgressIndicator()
                }

                is NetworkResult.Error<*> -> {
                    Text(text = "Error: ${result.data}")
                }
            }
        }
    }
}