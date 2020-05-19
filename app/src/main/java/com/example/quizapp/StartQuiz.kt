package com.example.quizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_start_quiz.*

class StartQuiz : AppCompatActivity() , View.OnClickListener {

    private var currentPos: Int = 1
    private var questionList: ArrayList<QuestionPaper>? = null
    private var selectedoption: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_quiz)

        questionList = Constants.getQuestions()
        setQuestion(currentPos)

        optionOne.setOnClickListener(this)
        optionTwo.setOnClickListener(this)
        optionThree.setOnClickListener(this)
        optionFour.setOnClickListener(this)
        submit.setOnClickListener {
            currentPos++;
            if (currentPos <=2 ) {setQuestion(currentPos)}
            else {Toast.makeText(this, "Congratulations, Questions are Completed", Toast.LENGTH_LONG).show()}
        }
    }

    private fun setQuestion(quesno: Int){

        currentPos = quesno
        val question = questionList!![currentPos - 1]

        progressBar.progress = currentPos
        progress.text = "$currentPos" + "/" + progressBar.max

        defaultOption()

        quesid.text = question!!.question
        flag.setImageResource(question.image)
        optionOne.text = question.optionOne
        optionTwo.text = question.optionTwo
        optionThree.text = question.optionThree
        optionFour.text = question.optionFour
    }

    private fun defaultOption(){
        val options = ArrayList<TextView>()
        options.add(0, optionOne)
        options.add(1, optionTwo)
        options.add(2, optionThree)
        options.add(3, optionFour)

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.optionborder)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.optionOne->{selectedOption(optionOne, 1)}
            R.id.optionTwo->{selectedOption(optionTwo, 2)}
            R.id.optionThree->{selectedOption(optionThree, 3)}
            R.id.optionFour->{selectedOption(optionFour, 4)}
        }
    }

    private fun selectedOption(tv: TextView, selected: Int){
        defaultOption()
        selectedoption = selected
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selectedoption)
    }
}
