package com.example.translator

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.translator.api.ApiRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val repo: ApiRepo
) : ViewModel() {
    val wordText = mutableStateOf("")
    private val wordInput = Channel<String>(Channel.CONFLATED)
    val tranData = repo.translatorData
    val isLoading = mutableStateOf(false)

    //init {
      //  onWordChange()
    //}

    fun onWordChange() {
        viewModelScope.launch {
            Log.d("ViewModel", "onWordChange called")
            repo.onTranslate(wordText.value)
        }
    }

    fun onWordEnter(word: String) {
        wordText.value = word
        onWordChange()
    }
}
