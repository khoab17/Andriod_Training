package com.syedabdullah.affirmation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.syedabdullah.affirmation.adapter.ItemAdapter
import com.syedabdullah.affirmation.data.DataSource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val myDataset= DataSource().loadAffirmation()
        val studentData=DataSource().getStudentData()

        val recyclerView=findViewById<RecyclerView>(R.id.rv_affirmation)
        recyclerView.adapter=ItemAdapter(this,studentData)
        recyclerView.setHasFixedSize(true)
    }
}
