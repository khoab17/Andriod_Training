package com.syedabdullah.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.syedabdullah.mvvm.adapter.BlogRecyclerAdapter
import com.syedabdullah.mvvm.model.Blog
import com.syedabdullah.mvvm.viewmodel.BlogViewModel
import com.syedabdullah.mvvm.viewmodel.BlogViewModelProviderFactory
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var layoutManager=LinearLayoutManager(this)
    private lateinit var viewModel: BlogViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView=findViewById(R.id.recycler)
        val factory=BlogViewModelProviderFactory()
        viewModel=ViewModelProvider(this,factory)[BlogViewModel::class.java]
        button=findViewById(R.id.button)

        button.setOnClickListener {
            addData()
        }
        initializeAdapter()
    }



    private fun initializeAdapter(){
        recyclerView.layoutManager=layoutManager
        observeData()
    }
    private fun observeData(){
        viewModel.list.observe(this) {
            recyclerView.adapter = BlogRecyclerAdapter(this, viewModel, it)
        }
    }

    private fun addData(){
        val txt=findViewById<EditText>(R.id.titletxt)
        val title=txt.text.toString()
        if(title.isBlank()){
            Toast.makeText(this, "Enter Value", Toast.LENGTH_SHORT).show()
        }
        else{
            val blog=Blog(title, getCurrentDate())
            viewModel.addBlog(blog)
            txt.text.clear()
        }
    }

    private fun getCurrentDate():String{
        val date=SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate=date.format(Date())
        return currentDate.toString()
    }


}