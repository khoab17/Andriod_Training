
package com.bjit.retrofitexample.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bjit.retrofitexample.model.MarsPhoto
import com.bjit.retrofitexample.network.MarsApi
import kotlinx.coroutines.launch

enum class MarsApiStatus { LOADING, ERROR, DONE }


class OverviewViewModel() : ViewModel() {

    private val _status = MutableLiveData<MarsApiStatus>()

    val status: LiveData<MarsApiStatus> = _status

    private val _photos = MutableLiveData<List<MarsPhoto>>()

    val photos: LiveData<List<MarsPhoto>> = _photos



    init {
        //checkInternetConnection(context)
        if(OverviewFragment.isConnected)
            getMarsPhotos()
        else {
            _status.value = MarsApiStatus.ERROR
            _photos.value = listOf()
        }
    }

     fun getMarsPhotos() {

        viewModelScope.launch {
            _status.value = MarsApiStatus.LOADING
            try {
                _photos.value = MarsApi.retrofitService.getPhotos()
                _status.value = MarsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MarsApiStatus.ERROR
                _photos.value = listOf()
            }
        }
    }


}
