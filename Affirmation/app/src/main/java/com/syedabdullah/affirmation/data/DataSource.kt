package com.syedabdullah.affirmation.data

import com.syedabdullah.affirmation.R
import com.syedabdullah.affirmation.model.Affirmation
import com.syedabdullah.affirmation.model.Student

class DataSource(){
    fun loadAffirmation():List<Affirmation>{
        return listOf(
            Affirmation(R.string.affirmation1),
            Affirmation(R.string.affirmation2),
            Affirmation(R.string.affirmation3),
            Affirmation(R.string.affirmation4),
            Affirmation(R.string.affirmation5),
            Affirmation(R.string.affirmation6),
            Affirmation(R.string.affirmation7),
            Affirmation(R.string.affirmation8),
            Affirmation(R.string.affirmation9),
            Affirmation(R.string.affirmation10),
        )
    }

    fun getStudentData():List<Student>{
        return listOf(
            Student(1,"Abir khan","A+",R.drawable.student01),
            Student(2,"Abir khan","O+",R.drawable.student03),
            Student(3,"Adam Sandler","O+",R.drawable.student04),
            Student(4,"Jennifer","B+",R.drawable.student01),
            Student(5,"Adam Kane","A-",R.drawable.student02),
            Student(6,"Tamim","B-",R.drawable.student03),
            Student(7,"James Milner","A-",R.drawable.student04),
            Student(8,"David Mill","A+",R.drawable.student01),
            Student(9,"Kyle Walker","O+",R.drawable.student02),
            Student(10,"Robert","O+",R.drawable.student03),
            Student(11,"James Madison","A-",R.drawable.student04),
            Student(12,"Tim paine","B+",R.drawable.student01),
            Student(13,"Tim Cook","B+",R.drawable.student02),
            Student(14,"Robert","A+",R.drawable.student03),
            Student(15,"Tim Cahill","B+",R.drawable.student04),
            Student(16,"James Madison","A-",R.drawable.student01),
            Student(17,"Tim paine","B+",R.drawable.student02),
            Student(18,"Tim Cook","B+",R.drawable.student03),
            Student(19,"Robert","A+",R.drawable.student04),
            Student(20,"Tim Cahill","B+",R.drawable.student01)
        )
    }
}
