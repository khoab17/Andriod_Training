package com.syedabdullah.marsphotos.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syedabdullah.marsphotos.network.MarsApi
import kotlinx.coroutines.launch

class OverviewViewModel:ViewModel(){
    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()

    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status

    init {
        getMarsPhotos()
    }


    private fun getMarsPhotos() {
        viewModelScope.launch {
            try {
                val listResult = MarsApi.retrofitService.getPhotos()
                _status.value = listResult.toString()
            } catch (e: Exception) {
                _status.value ="Error ${e.message}"
            }
        }

    }
}