package com.syedabdullah.affirmation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView

class StudentsDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students_details)

        val bundle:Bundle?=intent.extras
        val name: TextView=findViewById(R.id.tv_student_name)
        val id: TextView?=findViewById(R.id.tv_student_id)
        val bloodGroup: TextView?=findViewById(R.id.tv_blood_group)
        val imageView: ImageView=findViewById(R.id.iv_student_photo)

        Log.d("test","student activity: $id")

        id?.text=getString(R.string.student_id,bundle?.getString("student_id"))
        name?.text=getString(R.string.student_name,  bundle?.getString("student_name"))
        bloodGroup?.text=getString(R.string.student_blood_group,bundle?.getString("student_blood_group"))
        bundle?.getString("image_resource")?.let { imageView?.setImageResource(it.toInt()) }
    }
}