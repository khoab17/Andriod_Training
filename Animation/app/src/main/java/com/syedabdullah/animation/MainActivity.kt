package com.syedabdullah.animation
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import com.syedabdullah.animation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonOne.setOnClickListener{
            Toast.makeText(this,"Button one clicked!",Toast.LENGTH_SHORT).show()
            rotate(binding.imageViewStar)
        }
        binding.buttonTwo.setOnClickListener{
            Toast.makeText(this,"Button two is clicked!",Toast.LENGTH_SHORT).show()
            rotate(binding.textviewSample)
        }

        binding.buttonThree.setOnClickListener{
            colorized(binding.textviewSample.rootView)
        }

        binding.buttonFour.setOnClickListener{
            fadeIn(binding.textviewSampleTwo)
        }

        binding.buttonFive.setOnClickListener{
            slideOut(binding.textviewSampleTwo)
        }

        binding.buttonSix.setOnClickListener{
            bounce(binding.imageViewStar)
        }
        binding.buttonSeven.setOnClickListener{
                scalar(binding.textviewSample)
        }
    }


    private fun rotate(view:View){
        val animator= AnimationUtils.loadAnimation(this, R.anim.rotate)
        view.startAnimation(animator)
    }

    private fun slideOut(view:TextView){
        val animator=AnimationUtils.loadAnimation(this,R.anim.slide_out)
        view.startAnimation(animator)

    }

    private fun fadeIn(view:View){
        val animator=AnimationUtils.loadAnimation(this,R.anim.fade_in)
        view.startAnimation(animator)
    }
    private fun bounce(view: View){
        val animator=AnimationUtils.loadAnimation(this,R.anim.bounce)
        view.startAnimation(animator)
    }

    @SuppressLint("ObjectAnimatorBinding")
    private fun colorized(view: View) {
        val animator = ObjectAnimator.ofArgb(
            view,
            "backgroundColor",Color.BLUE,Color.CYAN,Color.WHITE
        )
        animator.duration = 1500
        animator.repeatCount = 2
        animator.repeatMode = ObjectAnimator.RESTART
        animator.start()
    }

    private fun scalar(view:View) {
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.8f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.8f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(view, scaleX, scaleY)
        animator.duration=1000
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.start()
    }

}