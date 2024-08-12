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
class SecondViewModel @Inject constructor() : ViewModel(), BaseViewModel {
    private val _text = MutableStateFlow("I don't know your name¯\\_(ツ)_/¯")
    val text = _text.asStateFlow()

    override fun onUpdateText(text: String) {
        _text.value = "Now I know your name, $text:D"
    }
}