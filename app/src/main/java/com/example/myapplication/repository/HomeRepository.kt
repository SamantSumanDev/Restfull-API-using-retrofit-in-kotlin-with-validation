package com.example.myapplication.repository

import com.example.myapplication.api.ApiService
import com.example.myapplication.model.PostRequest
import com.example.myapplication.model.Postapirequest

object HomeRepository {
    suspend fun getlistUnknoen(api: ApiService) = api.getlistunknown()

    suspend fun postRegister(param : PostRequest,api: ApiService) = api.postRegister(param)
}