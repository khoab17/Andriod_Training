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
            Student(1,"Abir khan","A+"),
            Student(1,"Abir khan","B+"),
            Student(2,"Abir khan","O+"),
            Student(3,"Adam Sandler","O+"),
            Student(4,"Jennifer","B+"),
            Student(5,"Adam Kane","A-"),
            Student(6,"Tamim","B-"),
            Student(7,"James Milner","A-"),
            Student(8,"David Mill","A+"),
            Student(9,"Kyle Walker","O+"),
            Student(10,"Robert","O+"),
            Student(11,"James Madison","A-"),
            Student(12,"Tim paine","B+"),
            Student(13,"Tim Cook","B+"),
            Student(14,"Robert","A+"),
            Student(15,"Tim Cahill","B+"),
            Student(16,"James Madison","A-"),
            Student(17,"Tim paine","B+"),
            Student(18,"Tim Cook","B+"),
            Student(19,"Robert","A+"),
            Student(20,"Tim Cahill","B+")
        )
    }
}
