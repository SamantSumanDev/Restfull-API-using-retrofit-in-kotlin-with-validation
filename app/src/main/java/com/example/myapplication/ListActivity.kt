package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.listAdapter
import com.example.myapplication.api.ApiControler
import com.example.myapplication.model.Data
import com.example.myapplication.model.ListDataResponse
import com.example.myapplication.model.ListdataRes2
import com.example.myapplication.util.ApiState
import com.example.myapplication.viewModel.ListViewModel

class ListActivity : AppCompatActivity() {
private lateinit var adapter: listAdapter<ListdataRes2>

    private val listviewModel : ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

//        getApiParam()
        getApiList()
//        observergetApi()
        observerList()
      recycleview()


    }




//    private fun getApiParam() {
//        Log.d("tag","accept params")
//        listviewModel .Listuser( api = ApiControler.getApi())
//    }
//    private fun observergetApi():Unit= with(listviewModel ){
//        ListuserLiveData.observe(this@ListActivity) {
//                state-> when (state){
//            is ApiState.Loading->{
////                showProgressBar(true)
//                Log.d("tag","loading")
//            }
//            is  ApiState.Success->{
////                showProgressBar(false)
//                Log.d("tag","sucess")
//                Log.d("listModel",state.data.toString())
//            }
//            is ApiState.Error->{
////                showProgressBar(false)
//                Log.d("tag", "error")
//            }
//            else-> {
//                Log.d("ta","default")
//            }
//        }
//        }
//
//    }



    private fun getApiList() {
        Log.d("tag","accept params")
        listviewModel.list( api = ApiControler.getApi())
    }





    private fun observerList():Unit= with(listviewModel ){
        listuserlistLiveData.observe(this@ListActivity) {
                state-> when (state){
            is ApiState.Loading->{
//                showProgressBar(true)
                Log.d("tag","loading")
                Toast.makeText(applicationContext,"List loading",Toast.LENGTH_SHORT).show()
            }
            is  ApiState.Success->{
//                showProgressBar(false)
                Log.d("tag","sucess")
                Log.d("tag1",state.data.toString())
                adapter.setData(state.data.data)




            }
            is ApiState.Error->{
//                showProgressBar(false)
                Log.d("tag", "error")
                Toast.makeText(applicationContext,"List error",Toast.LENGTH_LONG).show()

            }
            else-> {
                Log.d("ta","default")
                Toast.makeText(applicationContext,"List default",Toast.LENGTH_SHORT).show()
            }
        }
        }

    }
private fun recycleview() {


    val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
    recyclerview.layoutManager = LinearLayoutManager(applicationContext)
    adapter = listAdapter(emptyList())
    recyclerview.adapter = adapter
}
   /* private fun setRecylclerView(data: ArrayList<ListDataResponse> = ArrayList<ListDataResponse>()) {



    }
*/
}