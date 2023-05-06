package com.example.retrofitapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.retrofitapp.Adapters.RecyclerAdapter
import com.example.retrofitapp.Retrofit.PosDto
import com.example.retrofitapp.Retrofit.RetrofitApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailedActivity : AppCompatActivity() {
    lateinit var postid:TextView
    lateinit var postTitle:TextView
    lateinit var postbody:TextView

    lateinit var activity:DetailedActivity
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)
        activity=this
        postid=findViewById(R.id.detailed_postid)
        postTitle=findViewById(R.id.detailed_posttitle)
        postbody=findViewById(R.id.detailed_postbody)



        var intentID=intent.getStringExtra("postid")
        if( intentID!=null){

            val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create()).build()

            var    api: RetrofitApi = retrofit.create( RetrofitApi::class.java)


            var   calllist: Call< PosDto> = api.getpost(intentID)



            calllist.enqueue(object : Callback<PosDto?> {
                override fun onResponse(call: Call<PosDto?>, response: Response<PosDto?>) {
                    if(response.isSuccessful){
                        postid .text="Post ID :"+response.body()?.id
                        postTitle  .text= response.body()?.title
                        postbody   .text=  response.body()?.body



                    }


                }

                override fun onFailure(call: Call<PosDto?>, t: Throwable) {
                    print(call)
                    Toast.makeText( activity,call.toString(),Toast.LENGTH_SHORT).show()




                }
            })









         }



    }


}