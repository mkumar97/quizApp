package com.example.quizapp

import android.content.Context
import android.content.Intent
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
    private var score = 0
    private var username: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_quiz)

        username = intent.getStringExtra(Constants.USER_NAME)
        questionList = Constants.getQuestions()
        setQuestion()

        optionOne.setOnClickListener(this)
        optionTwo.setOnClickListener(this)
        optionThree.setOnClickListener(this)
        optionFour.setOnClickListener(this)
        submit.setOnClickListener(this)
    }

    private fun setQuestion(){

        // currentPos = quesno
        val question = questionList!![currentPos - 1]

        progressBar.progress = currentPos
        progress.text = "$currentPos" + "/" + progressBar.max

        defaultOption()

        if (selectedoption == 0) {submit.text = "SUBMIT"}

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
            R.id.submit-> {
                if (selectedoption == 0) {
                    currentPos ++

                    when {
                        currentPos <= questionList!!.size ->
                        { setQuestion() }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.CORRECT_ANSWERS, score)
                            intent.putExtra(Constants.USER_NAME, username)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, questionList!!.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
                else {
                    val question = questionList?.get(currentPos - 1)
                    if(question!!.correctAnswer != selectedoption){
                        answerView(selectedoption, R.drawable.wrongoptionborder)
                    }else { score ++ }
                    answerView(question.correctAnswer, R.drawable.correctoptionborder)

                    if(currentPos == questionList!!.size){
                        submit.text = "FINISH"
                    }else{
                        submit.text = "GO TO NEXT QUESTION"
                    }
                    selectedoption = 0
                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int){
        when(answer){
            1-> {optionOne.background = ContextCompat.getDrawable(this, drawableView)}
            2-> {optionTwo.background = ContextCompat.getDrawable(this, drawableView)}
            3-> {optionThree.background = ContextCompat.getDrawable(this, drawableView)}
            4-> {optionFour.background = ContextCompat.getDrawable(this, drawableView)}
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
