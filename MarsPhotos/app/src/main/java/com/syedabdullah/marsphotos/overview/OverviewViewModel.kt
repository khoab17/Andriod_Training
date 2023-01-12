package com.syedabdullah.marsphotos.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syedabdullah.marsphotos.network.MarsApi
import com.syedabdullah.marsphotos.model.MarsPhoto
import kotlinx.coroutines.launch

class OverviewViewModel:ViewModel(){
    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<MarsPhoto>()

    // The external immutable LiveData for the request status
    val status: LiveData<MarsPhoto> = _status

    init {
        getMarsPhotos()
    }


    private fun getMarsPhotos() {
        viewModelScope.launch {
            try {
                val listResult = MarsApi.retrofitService.getPhotos()
                _status.value = listResult[0]
            } catch (e: Exception) {
                Log.d("check", "getMarsPhotos: ${e.message}")
            }
        }

    }
}