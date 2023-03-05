package com.sardordev.quotesapp.veiwmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sardordev.quotesapp.data.model.AloneBody
import com.sardordev.quotesapp.data.model.EmptyBody
import com.sardordev.quotesapp.domain.repository.AppRespository
import com.sardordev.quotesapp.utils.ResultEvent
import com.sardordev.quotesapp.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val appRespository: AppRespository) : ViewModel() {
    private val _allobserver = MutableStateFlow<UiEvent>(UiEvent.Empty)
    val allobserver: StateFlow<UiEvent> get() = _allobserver


    fun  getAllTopics(emptyBody: EmptyBody){
        viewModelScope.launch {
            _allobserver.value = UiEvent.Loading
            val result = appRespository.getAllTopics(emptyBody)
            when (result) {
                is ResultEvent.Error -> {
                    _allobserver.value = UiEvent.Error(result.message)
                }
                is ResultEvent.Success -> {
                    _allobserver.value = UiEvent.Success(result.data)
                }
            }
        }
    }


}