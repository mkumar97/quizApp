package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val username = intent.getStringExtra(Constants.USER_NAME)
        displayname.text = "Congratulations! $username"

        val total_ques = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correct_ans = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)
        displayscore.text = "You guessed $correct_ans correctly out of $total_ques"

        refresh.setOnClickListener {
            val intent = Intent(this, StartQuiz::class.java)
            intent.putExtra(Constants.USER_NAME, username)
            startActivity(intent)
        }
    }
}
