package com.example.funwithflags

import android.content.res.Resources

object MyConstants {

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"
    private const val questionText = "Which country is this flag of"
    //private val questionText = Resources.getSystem().getString(R.string.question)


    val questions = listOf(
        Question(
            1, questionText,
            R.drawable.ic_flag_of_argentina,
            "Argentina","Australia","Armenia","Austria",
            1
        ),

        Question(
            2, questionText,
            R.drawable.ic_flag_of_australia,
            "Angola","Austria","Australia","Armenia",
            3
        ),

        Question(
            3, questionText,
            R.drawable.ic_flag_of_brazil,
            "Belarus","Belize","Brunei","Brazil",
            4
        ),

        Question(
            4, questionText,
            R.drawable.ic_flag_of_belgium,
            "Bahamas","Belgium","Barbados","Belize",
            2
        ),

        Question(
            5, questionText,
            R.drawable.ic_flag_of_fiji,
            "Gabon","France","Fiji","Finland",
            3
        ),

        Question(
            6 , questionText,
            R.drawable.ic_flag_of_germany,
            "Germany","Georgia","Greece","none of these",
            1
        ),

        Question(
            7, questionText,
            R.drawable.ic_flag_of_denmark,
            "Dominica","Egypt","Denmark","Ethiopia",
            3
        ),

        Question(
            8, questionText,
            R.drawable.ic_flag_of_india,
            "Ireland","Iran","Hungary","India",
            4
        ),

        Question(
            9, questionText,
            R.drawable.ic_flag_of_new_zealand,
            "Australia","New Zealand","Great Britain","Japan",
            1
        ),

        Question(
            10, questionText,
            R.drawable.ic_flag_of_kuwait,
            "Kuwait","Jordan","Sudan","Palestine",
            1
        )
    )
}