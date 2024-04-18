package com.example.funwithflags

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.funwithflags.databinding.ActivityMainBinding
import com.example.funwithflags.databinding.ActivityQuizQuestionsBinding
import org.w3c.dom.Text

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private val binding by lazy {
        ActivityQuizQuestionsBinding.inflate(layoutInflater)
    }

    private var currentPosition: Int = 0
    private val questionsList: ArrayList<Question> = arrayListOf()
    private var correctAnswers: Int = 0
    private var userName: String = ""
    private var selectedOptionPosition: Int = 0
    private var progressBar: ProgressBar? = null
    private var progressTv: TextView? = null
    private var questionTv: TextView? = null
    private var flagIv: ImageView? = null
    private var optionOneTv: TextView? = null
    private var optionTwoTv: TextView? = null
    private var optionThreeTv: TextView? = null
    private var optionFourTv: TextView? = null
    private var submitBtn: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //progressTv = binding.progressTv
        progressBar = binding.progressBar
        questionTv = binding.questionTv
        flagIv = binding.flagIv
        optionOneTv = binding.optionOneTv
        optionTwoTv = binding.optionTwoTv
        optionThreeTv = binding.optionThreeTv
        optionFourTv = binding.optionFourTv
        submitBtn = binding.submitBtn
        questionsList.addAll(MyConstants.questions)

        //Activity extends AppCompatActivity and also IMPLEMENTS Interface View method OnClickListener
        optionOneTv?.setOnClickListener(this)
        optionTwoTv?.setOnClickListener(this)
        optionThreeTv?.setOnClickListener(this)
        optionFourTv?.setOnClickListener(this)
        submitBtn?.setOnClickListener(this)

        userName = intent.getStringExtra(MyConstants.USER_NAME).toString()
        setQuestion()
    }


    override fun onClick(view: View) {

        when(view?.id) {
            optionOneTv?.id -> {
                optionOneTv?.let {
                    selectedOptionView(it, 1)
                }
            }

            optionTwoTv?.id -> {
                optionTwoTv?.let {
                    selectedOptionView(it, 2)
                }
            }

            optionThreeTv?.id -> {
                optionThreeTv?.let {
                    selectedOptionView(it, 3)
                }
            }

            optionFourTv?.id -> {
                optionFourTv?.let {
                    selectedOptionView(it, 4)
                }
            }

            submitBtn?.id -> {
                progressBar?.progress = currentPosition
                if(selectedOptionPosition == 0) {
                    currentPosition++

                    when {
                        currentPosition <= questionsList.size - 1 -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this@QuizQuestionsActivity, ResultActivity::class.java)
                            intent.putExtra(MyConstants.USER_NAME, userName)
                            intent.putExtra(MyConstants.CORRECT_ANSWERS, correctAnswers)
                            intent.putExtra(MyConstants.TOTAL_QUESTIONS, questionsList.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val question = questionsList[currentPosition]

                    if(question.correctAnswer != selectedOptionPosition) {
                        answerView(selectedOptionPosition, R.drawable.wrong_option_border_bg)
                    } else {
                        correctAnswers++
                    }

                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if(currentPosition == questionsList.size - 1) {
                        submitBtn?.text = "FINISH"
                    } else {
                        submitBtn?.text = "GO TO NEXT QUESTION"
                    }

                    selectedOptionPosition = 0
                }
            }
        }
    }


    private fun defaultOptionsView() {
        val options = listOf<TextView?>(
            optionOneTv, optionTwoTv, optionThreeTv, optionFourTv
        )

        for (option in options) {
            option?.setTextColor(Color.parseColor("#7A8089"))
            option?.typeface = Typeface.DEFAULT
            option?.background = ContextCompat.getDrawable(this@QuizQuestionsActivity,R.drawable.default_option_border_bg)
        }
    }

    private fun setQuestion() {
        val question: Question = questionsList[currentPosition]

        defaultOptionsView()

        if(currentPosition == questionsList.size) {
            submitBtn?.text = "FINISH"
        } else {
            submitBtn?.text = "SUBMIT"
        }

        progressBar?.progress = currentPosition

        val no = currentPosition + 1

        questionTv?.text = question.question
        flagIv?.setImageResource(question.image)
        optionOneTv?.text = question.optionOne
        optionTwoTv?.text = question.optionTwo
        optionThreeTv?.text = question.optionThree
        optionFourTv?.text = question.optionFour
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()

        selectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))

        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this@QuizQuestionsActivity, R.drawable.selected_option_border_bg
        )
    }

    private fun answerView(answer: Int, drawableView: Int) {

        when(answer) {
            1 -> {
                optionOneTv?.background = ContextCompat.getDrawable(this@QuizQuestionsActivity, drawableView)
            }

            2 -> {
                optionTwoTv?.background = ContextCompat.getDrawable(this@QuizQuestionsActivity, drawableView)
            }

            3 -> {
                optionThreeTv?.background = ContextCompat.getDrawable(this@QuizQuestionsActivity, drawableView)
            }

            4 -> {
                optionFourTv?.background = ContextCompat.getDrawable(this@QuizQuestionsActivity, drawableView)
            }
        }
    }
}