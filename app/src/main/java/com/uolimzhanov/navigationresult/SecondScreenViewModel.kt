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
class SecondViewModel @Inject constructor() : ViewModel() {
    private val _text = MutableStateFlow("I don't know your name¯\\_(ツ)_/¯")
    val text = _text.asStateFlow()

    fun onUpdateText(text: String) {
        _text.value = "Now I know your name, $text:D"
    }
}