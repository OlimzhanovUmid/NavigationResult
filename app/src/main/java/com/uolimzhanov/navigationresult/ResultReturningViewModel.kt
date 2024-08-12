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
class ResultReturningViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(ResultReturningScreenState())
    val state = _state.asStateFlow()

    fun onNameChange(name: String) {
        _state.value = _state.value.copy(username = name)
    }
}