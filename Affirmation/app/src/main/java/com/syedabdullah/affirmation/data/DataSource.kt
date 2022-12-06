package com.syedabdullah.affirmation.data

import com.syedabdullah.affirmation.R
import com.syedabdullah.affirmation.model.Student

class DataSource(){
    fun getStudentData():List<Student>{
        return listOf(
            Student(1,"Abir khan","A+",R.drawable.student01,R.string.bio_details),
            Student(2,"Abir khan","O+",R.drawable.student03,R.string.bio_details),
            Student(3,"Adam Sandler","O+",R.drawable.student04,R.string.bio_details),
            Student(4,"Jennifer","B+",R.drawable.student01,R.string.bio_details),
            Student(5,"Adam Kane","A-",R.drawable.student02,R.string.bio_details),
            Student(6,"Tamim","B-",R.drawable.student03,R.string.bio_details),
            Student(7,"James Milner","A-",R.drawable.student04,R.string.bio_details),
            Student(8,"David Mill","A+",R.drawable.student01,R.string.bio_details),
            Student(9,"Kyle Walker","O+",R.drawable.student02,R.string.bio_details),
            Student(10,"Robert","O+",R.drawable.student03,R.string.bio_details),
            Student(11,"James Madison","A-",R.drawable.student04,R.string.bio_details),
            Student(12,"Tim paine","B+",R.drawable.student01,R.string.bio_details),
            Student(13,"Tim Cook","B+",R.drawable.student02,R.string.bio_details),
            Student(14,"Robert","A+",R.drawable.student03,R.string.bio_details),
            Student(15,"Tim Cahill","B+",R.drawable.student04,R.string.bio_details),
            Student(16,"James Madison","A-",R.drawable.student01,R.string.bio_details),
            Student(17,"Tim paine","B+",R.drawable.student02,R.string.bio_details),
            Student(18,"Tim Cook","B+",R.drawable.student03,R.string.bio_details),
            Student(19,"Robert","A+",R.drawable.student04,R.string.bio_details),
            Student(20,"Tim Cahill","B+",R.drawable.student01,R.string.bio_details)
        )
    }
}
