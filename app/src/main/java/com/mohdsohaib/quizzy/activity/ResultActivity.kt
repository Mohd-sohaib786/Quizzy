package com.mohdsohaib.quizzy.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.google.gson.Gson
import com.mohdsohaib.quizzy.R
import com.mohdsohaib.quizzy.models.Quiz
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    lateinit var quiz : Quiz
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        setUpViews()
    }

    private fun setUpViews() {
         val quizData = intent.getStringExtra("QUIZ")
         quiz = Gson().fromJson<Quiz>(quizData, Quiz::class.java)
         calculateScore()
         setAnswerView()
    }

    private fun setAnswerView() {
        val builder = StringBuilder("")
        for (entry in quiz.questions.entries) {
            val question = entry.value
            builder.append("<font color'#18206F'><b>Question: ${question.description}</b></font><br/><br/>")
            builder.append("<font color='#009688'>Answer: ${question.answer}</font><br/><br/>")
        }
        txtAnswer.text = Html.fromHtml(builder.toString(), Html.FROM_HTML_MODE_COMPACT);
    }

    private fun calculateScore() {
        var score = 0
        for (entry in quiz.questions.entries) {
            val question = entry.value
            if (question.answer == question.userAnswer) {
                score += 10
            }
        }
        txtScore.text = "Your Score : $score"
    }
}