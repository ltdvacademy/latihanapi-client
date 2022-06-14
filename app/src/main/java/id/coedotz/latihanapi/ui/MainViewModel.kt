package id.coedotz.latihanapi.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.coedotz.latihanapi.model.Data
import id.coedotz.latihanapi.network.DataApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val data = MutableLiveData<List<Data>>()
    private val status = MutableLiveData<DataApi.ApiStatus>()

    init {
        //data.value = initData()
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            status.postValue(DataApi.ApiStatus.LOADING)
            try {
                data.postValue(DataApi.service.getData())
                status.postValue(DataApi.ApiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.postValue(DataApi.ApiStatus.FAILED)
            }
        }
    }

    fun getData(): LiveData<List<Data>> = data
    fun getStatus(): LiveData<DataApi.ApiStatus> = status
}