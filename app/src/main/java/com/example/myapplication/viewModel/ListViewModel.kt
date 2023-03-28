package com.example.myapplication.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.ApiService
import com.example.myapplication.model.*
import com.example.myapplication.repository.HomeRepository
import com.example.myapplication.repository.ListRepository
import com.example.myapplication.util.ApiState
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ListViewModel() : ViewModel() {
    private val _Listuser = MutableLiveData<ApiState<ListuserResponse>>()
    val ListuserLiveData: LiveData<ApiState<ListuserResponse>> = _Listuser

    private val _listuserlist = MutableLiveData<ApiState<ListdataRes2>>()
    val listuserlistLiveData: LiveData<ApiState<ListdataRes2>> = _listuserlist


    fun Listuser(api: ApiService){
        _Listuser.value = ApiState.loading()
        viewModelScope.launch{
            try {
//                val res = ListRepository.getlistUnknoen(api)
//                _Listuser.postValue(ApiState.success(res))
            }catch (e:Exception){

                Log.d("tag", "exp: $e")

                _Listuser.postValue(
                    ApiState.error(  e.localizedMessage ?: "Something went wrong")
                )
            }
        }
    }

    fun list( api: ApiService){
        _listuserlist.value = ApiState.loading()
        viewModelScope.launch{
            try {
                val res = ListRepository.getUserList(api)
              _listuserlist.postValue(ApiState.success(res))

            } catch (e: HttpException) {
                Log.d("tag2", "exp: ${e.code()} ${e.response()}")
                _listuserlist.postValue(ApiState.error("HTTP ${e.code()} error"))
            } catch (e: Exception) {
                Log.d("tag2", "exp: $e")
                _listuserlist.postValue(ApiState.error(e.localizedMessage ?: "Something went wrong"))
            }
        }
    }



}