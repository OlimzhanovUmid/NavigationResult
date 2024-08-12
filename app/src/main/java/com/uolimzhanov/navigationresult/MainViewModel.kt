package com.uolimzhanov.navigationresult

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * Created by uolimzhanov on 12.08.2024
 */
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _greetingMessage = MutableStateFlow("Hello, user!")
    val greetingMessage = _greetingMessage.asStateFlow()

    fun onUpdateText(text: String) {
        _greetingMessage.value = "Hello, $text!"
    }
}