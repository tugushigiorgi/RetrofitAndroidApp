package com.example.retrofitapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.retrofitapp.Adapters.RecyclerAdapter
import com.example.retrofitapp.Retrofit.PosDto
import com.example.retrofitapp.Retrofit.RetrofitApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() ,adapterclicklistener {
lateinit var RecyclerViewXML:RecyclerView
lateinit var activity: MainActivity
    @SuppressLint("MissingInflatedId", "NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity =this
        RecyclerViewXML=findViewById(R.id.maincreacyclerview)

        RecyclerViewXML.layoutManager=   LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()

      var    api: RetrofitApi = retrofit.create( RetrofitApi::class.java)


       var   calllist: Call<List<PosDto>> = api.postlist()


        calllist.enqueue(object : Callback<List<PosDto>?> {
            override fun onResponse(call: Call<List<PosDto>?>, response: Response<List<PosDto>?>) {
                if(response.isSuccessful){


                    var adapter = response.body()?.let { RecyclerAdapter(it) }

                            adapter?.activity=activity
                    RecyclerViewXML.adapter=adapter

                }

            }

            override fun onFailure(call: Call<List<PosDto>?>, t: Throwable) {
                Toast.makeText( activity,call.toString(), Toast.LENGTH_SHORT).show()

            }
        })



    }

    override fun click(postid:String ) {
        var intent =Intent(this,DetailedActivity::class.java)
        intent.putExtra("postid",postid)



       startActivity(  intent)



    }
}