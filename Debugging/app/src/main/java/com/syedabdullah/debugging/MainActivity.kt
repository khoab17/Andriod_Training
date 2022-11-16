package com.syedabdullah.debugging

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView


private const val TAG="MainActivity"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val debugView:TextView=findViewById(R.id.debugTextView)
        debugView.text="Hello ,debugger!"
        // logging()
        //division()
        //first()

        logging()
        //castingInt()
        //copyList()
        //logging()
    }
    fun castingInt(){
        var s="123ghfg"
        val convert=s.toInt()
        Log.e(TAG, "${s.toString()} can not be converted")
    }
    fun copyList(){
        val nums= mutableListOf<Int>(1,2,3,4,5,6,7,8,9)
        var nums2= mutableListOf<Int>()
        for(i in 0..10){
            nums2.add(nums[i])
        }
        Log.e(TAG, "List is out of bound")
    }

    fun logging() {
        Log.e(TAG, "ERROR: a serious error like an app crash")
        Log.w(TAG, "WARN: warns about the potential for serious errors")
        Log.i(TAG, "INFO: reporting technical information, such as an operation succeeding")
        Log.d(TAG, "DEBUG: reporting technical information useful for debugging")
        Log.v(TAG, "VERBOSE: more verbose than DEBUG logs")
    }
    fun division() {
        val numerator = 60
        var denominator = 4
        repeat(5) {
            Log.v(TAG, "${numerator / denominator}")
            denominator--
        }
    }

    fun first() {
        second()
        Log.v(TAG, "1")
    }

    fun second() {
        third()
        Log.v(TAG, "2")
        fourth()
    }

    fun third() {
        Log.v(TAG, "3")
    }

    fun fourth() {
        Log.v(TAG, "4")
    }
}