package com.example.myapplication.repository

import com.example.myapplication.api.ApiService
import com.example.myapplication.model.PostRequest

object ListRepository {

//    suspend fun getlistUnknoen(api: ApiService) = api.getlistunknown()

    suspend fun getUserList(api: ApiService) = api.getUserList()
}