package saha.profile.subham.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import saha.profile.subham.api.Api
import saha.profile.subham.api.ApiClient
import saha.profile.subham.model.ApplicationData

class ProfileApplicationViewModel : ViewModel() {
    lateinit var applicationListLiveData : MutableLiveData<ApplicationData>

    init {
        applicationListLiveData = MutableLiveData()
    }

    fun applicationListObserver() : MutableLiveData<ApplicationData> {
        return applicationListLiveData
    }

    fun applications() {
        viewModelScope.launch(Dispatchers.IO) {
            val apiInstance = ApiClient.getInstance().create(Api::class.java)
            val response = apiInstance.getApplicationData("378")
            Log.e("Response",""+response.message)
            applicationListLiveData.postValue(response)
        }
    }
}