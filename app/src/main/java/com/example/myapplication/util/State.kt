package com.example.myapplication.util

sealed class ApiState<T> {
    class Loading<T> : ApiState<T>()
    data class Success<T>(val data: T) : ApiState<T>()
    data class Error<T>(val message: String) : ApiState<T>()

    companion object {
        fun <T> loading() = Loading<T>()
        fun <T> success(data: T): ApiState<T> = Success(data)
        fun <T> error(message: String) = Error<T>(message)
    }
}
sealed class GeneralState{
    object Loading : GeneralState()
    class Message(val msg:String) : GeneralState()
}