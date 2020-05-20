package com.example.quizapp

object Constants {

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "number"
    const val CORRECT_ANSWERS: String = "correct"

    fun getQuestions(): ArrayList<QuestionPaper> {
        val questionList = ArrayList<QuestionPaper>()
        val que = "Which country does this belong?"

        val que1 = QuestionPaper(1, que, R.drawable.australia,
            "Austria","USA", "Australia", "None", 3)

        val que2 = QuestionPaper(2, que, R.drawable.china, "Srilanka", "China",
        "India", "Nepal", 2)

        questionList.add(que1)
        questionList.add(que2)
        return questionList
    }

}