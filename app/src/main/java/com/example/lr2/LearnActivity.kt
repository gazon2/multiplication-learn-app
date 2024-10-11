package com.example.lr2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
class LearnActivity : ComponentActivity() {

    private var correctAnswers = 0
    private var currentExample = 0
    private lateinit var questionTextView: TextView
    private lateinit var answerEditText: EditText
    private var firstNumber = 0
    private var secondNumber = 0
    private var totalExamples = 20
    private var selectedNumber = 0
    private var mode = "all"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn)

        questionTextView = findViewById(R.id.questionTextView)
        answerEditText = findViewById(R.id.answerEditText)
        val submitButton = findViewById<Button>(R.id.submitButton)

        mode = intent.getStringExtra("mode") ?: "all"
        if (mode == "selected") {
            selectedNumber = intent.getIntExtra("selectedNumber", 2)
        }

        // Начать упражнение
        nextExample()

        submitButton.setOnClickListener {
            checkAnswer()
        }
    }

    private fun nextExample() {
        if (currentExample < totalExamples) {
            currentExample++
            if (mode == "all") {
                firstNumber = (2..9).random()
            } else {
                firstNumber = selectedNumber
            }
            secondNumber = (2..9).random()
            questionTextView.text = "Сколько будет: $firstNumber x $secondNumber?"
        } else {
            // Вывести результат
            val percentage = (correctAnswers * 100) / totalExamples
            Toast.makeText(this, "Тест завершён! Правильных ответов: $correctAnswers/$totalExamples. Результат: $percentage%", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun checkAnswer() {
        val userAnswerStr = answerEditText.text.toString()
        if (userAnswerStr.isEmpty()) {
            Toast.makeText(this, "Введите ответ", Toast.LENGTH_SHORT).show()
        } else {
            val userAnswer = userAnswerStr.toInt()
            if (userAnswer == firstNumber * secondNumber) {
                correctAnswers++
                Toast.makeText(this, "Правильный ответ!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Неверный ответ", Toast.LENGTH_SHORT).show()
            }
            answerEditText.text.clear()
            nextExample()
        }
    }
}