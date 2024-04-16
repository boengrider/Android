package com.example.funwithflags

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.widget.Button
import android.widget.TextView
import com.example.funwithflags.databinding.ActivityResultBinding
import android.content.Intent
import android.util.Log

class ResultActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityResultBinding.inflate(layoutInflater)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val tvName: TextView = binding.tvName
        val tvScore: TextView = binding.tvScore
        val btnFinish: Button = binding.btnFinish

        val userName = intent.getStringExtra(MyConstants.USER_NAME)
        Log.i("MyConstants",userName.toString())
        tvName.text = userName

        val totalQuestions = intent.getIntExtra(MyConstants.TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(MyConstants.CORRECT_ANSWERS, 0)

        tvScore.text =  resources.getString(R.string.score) + " " + correctAnswers + "/" + totalQuestions

        btnFinish.setOnClickListener {
            //
            startActivity(Intent(this@ResultActivity, MainActivity::class.java))
        }
    }
}