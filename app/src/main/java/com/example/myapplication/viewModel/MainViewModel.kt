package com.example.myapplication.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myapplication.api.ApiConstant
import com.example.myapplication.api.ApiService
import com.example.myapplication.model.ListuserResponse
import com.example.myapplication.model.PostRequest
import com.example.myapplication.model.Postapirequest
import com.example.myapplication.model.Postapiresponse
import com.example.myapplication.repository.HomeRepository
import com.example.myapplication.util.ApiState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response


class MainViewModel():ViewModel() {
    private val _Listuser= MutableLiveData<ApiState<ListuserResponse>>()
    val ListuserLiveData : LiveData<ApiState<ListuserResponse>> = _Listuser

    private val _Registeruser= MutableLiveData<ApiState<Postapiresponse>>()
    val RegisteruserLiveData : LiveData<ApiState<Postapiresponse>> = _Registeruser



    fun Listuser(api: ApiService){
        _Listuser.value = ApiState.loading()
        viewModelScope.launch{
            try {
                val res = HomeRepository.getlistUnknoen(api)

            }catch (e:Exception){

                Log.d("tag", "exp: $e")

                _Listuser.postValue(
                    ApiState.error(  e.localizedMessage ?: "Something went wrong")
                )
            }
        }
    }

    fun Regester(param:PostRequest,api: ApiService){
        _Registeruser.value = ApiState.loading()
        viewModelScope.launch{
            try {
                val res = HomeRepository.postRegister(param,api)
                _Registeruser.postValue(ApiState.success(res))
            } catch (e: HttpException) {
                Log.d("tag2", "exp: ${e.code()} ${e.response()}")
                _Registeruser.postValue(ApiState.error("HTTP ${e.code()} error"))
            } catch (e: Exception) {
                Log.d("tag2", "exp: $e")
                _Registeruser.postValue(ApiState.error(e.localizedMessage ?: "Something went wrong"))
            }
        }
    }
}