package com.example.bytechallengue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bytechallengue.api.APIService
import com.example.bytechallengue.api.MyDataItem
import com.example.bytechallengue.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val URL_API = "https://mocki.io/v1/"
class MainActivity : AppCompatActivity(), OnClickListener {

     lateinit var myAdapter: MyAdapter
     lateinit var linearLayoutManager: LinearLayoutManager
     private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        //Set name
        val welcome = findViewById<TextView>(R.id.welcome);
        val name = intent.getStringExtra("name")
        val message = ("Welcome "+ name);
        welcome.setText(message)

        getMyData();

    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(URL_API)
            .build()
            .create(APIService::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>
            ) {
                val responseBody = response.body()!!

                myAdapter = MyAdapter(baseContext, responseBody, this@MainActivity)
                myAdapter.notifyDataSetChanged()
                val rc = findViewById<RecyclerView>(R.id.recyclerView);
                rc.adapter = myAdapter
            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                Toast.makeText(applicationContext, "Fail", Toast.LENGTH_SHORT).show();
            }
        })
    }

    override fun onClick(dataID: String) {
        Toast.makeText(this, "$dataID", Toast.LENGTH_LONG).show()
    }


}




















