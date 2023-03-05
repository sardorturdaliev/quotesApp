package com.sardordev.quotesapp.veiwmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sardordev.quotesapp.data.entity.LikeEntity
import com.sardordev.quotesapp.data.model.AloneBody
import com.sardordev.quotesapp.domain.repository.AppRespository
import com.sardordev.quotesapp.utils.ResultEvent
import com.sardordev.quotesapp.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val appRespository: AppRespository) : ViewModel() {
    private val _aloneobserver = MutableStateFlow<UiEvent>(UiEvent.Empty)
    val aloneobserver: StateFlow<UiEvent> get() = _aloneobserver


    fun getAloneData(aloneBody: AloneBody) {
        viewModelScope.launch {
            _aloneobserver.value = UiEvent.Loading
            val result = appRespository.getAloneData(aloneBody)
            when (result) {
                is ResultEvent.Error -> {
                    _aloneobserver.value = UiEvent.Error(result.message)
                }
                is ResultEvent.Success -> {
                    _aloneobserver.value = UiEvent.Success(result.data)
                }
            }
        }
    }

    fun insertLike(likeEntity: LikeEntity){
        appRespository.insertData(likeEntity)
    }

}