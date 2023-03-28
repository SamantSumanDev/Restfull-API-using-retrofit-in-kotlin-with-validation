package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import com.example.myapplication.api.ApiControler
import com.example.myapplication.model.PostRequest
import com.example.myapplication.model.Postapirequest
import com.example.myapplication.util.ApiState
import com.example.myapplication.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val listviewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getApiParam()
        observergetApi()
        RequestParam()
        observerRequest()

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener() {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getApiParam(){
        Log.d("tag","accept params")
        listviewModel.Listuser( api = ApiControler.getApi())
    }
    private fun observergetApi():Unit= with(listviewModel ){
        ListuserLiveData.observe(this@MainActivity) {
                state-> when (state){
            is ApiState.Loading->{
//                showProgressBar(true)
                Log.d("tag","loading")
            }
            is  ApiState.Success->{
//                showProgressBar(false)
                Log.d("tag","sucess")
                Log.d("tag",state.data.toString())


            }
            is ApiState.Error->{
//                showProgressBar(false)
                Log.d("tag", "error")

            }
            else-> {
                Log.d("ta","default")
            }
        }
        }

    }

    private fun RequestParam(){
        val param = PostRequest(
            "eve.holt@reqres.in",
            "pistol"
        )
        Log.d("tag","accept params")
        listviewModel.Regester(param,ApiControler.getApi())
    }
    private fun observerRequest():Unit= with(listviewModel){
       RegisteruserLiveData.observe(this@MainActivity) {
                state-> when (state){
            is ApiState.Loading->{
 //              showProgressBar(true)
                Log.d("tag","loading")
            }
            is  ApiState.Success->{
//                showProgressBar(false)
                Log.d("tag","sucess")
                Toast.makeText(applicationContext,"Success", Toast.LENGTH_LONG).show()
                Log.d("tag",state.data.toString())


            }
            is ApiState.Error->{
//                showProgressBar(false)
                Log.d("tag", "error")

            }
            else-> {
                Log.d("tag","default")
            }
        }
        }

    }
}