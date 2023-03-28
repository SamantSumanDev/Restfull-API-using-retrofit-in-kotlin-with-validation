package com.example.myapplication.api

object ApiControler {
    fun getApi() : ApiService = ApiService.create()
}