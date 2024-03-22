package com.example.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.example.bmi.databinding.ActivityMainBinding
import java.math.BigDecimal
import kotlin.math.round
import kotlin.math.roundToLong

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //************************************
    //State holders
    //************************************
    private var heightValue: Float = 0.0f
    private var weightValue: Float = 0.0f
    private var bmi: Float = 0.0f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //*********************************************
        //Event handlers
        //*********************************************

        //Set on click listener for height plus button
        val heightEt = binding.heightEt
        binding.heightPlusBtn.setOnClickListener {
            if (heightEt.text.toString().isNotEmpty()) {
                heightValue = heightEt.text.toString().toFloat()
            }

            heightValue++
            heightEt.setText(heightValue.toString())


        }

        heightEt.setOnFocusChangeListener {
            view,focus -> Log.i("ViewBinding","Focus of the view $view has changed to $focus")
        }

        //Set on click listener for height minus button
        binding.heightMinusBtn.setOnClickListener {
            if (heightEt.text.toString().isNotEmpty()) {
                heightValue = heightEt.text.toString().toFloat()
            }

            if(heightValue > 0) {
                heightValue--
                heightEt.setText(heightValue.toString())
            }

        }

        //Set on click listener for weight plus  button
        val weightEt = binding.weightEt
        binding.weightPlusBtn.setOnClickListener {
            if (weightEt.text.toString().isNotEmpty()) {
                weightValue = weightEt.text.toString().toFloat()
            }

            weightValue++
            weightEt.setText(weightValue.toString())
        }

        //Set on IME_DONE action
        weightEt.setOnEditorActionListener {
            _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_DONE) {
                val height = heightEt.text.toString().toFloat()
                val weight = weightEt.text.toString().toFloat()
                val bmi = (weight/height.times(height))

                Toast.makeText(this, "$bmi", Toast.LENGTH_SHORT).show()
                displayBMIResult(bmi)
                true
            }
            false
        }

        //Set on click listener for weight minus button
        binding.weightMinusBtn.setOnClickListener {
            if (weightEt.text.toString().isNotEmpty()) {
                weightValue = weightEt.text.toString().toFloat()
            }

            if(weightValue > 0) {
                weightValue--
                weightEt.setText(weightValue.toString())
            }
        }

        binding.calculateBtn.setOnClickListener {
            val height = heightEt.text.toString().toFloat()
            val weight = weightEt.text.toString().toFloat()
            val bmi: Float = (weight/height.times(height))

            Toast.makeText(this,"$bmi",Toast.LENGTH_LONG).show()
            displayBMIResult(bmi)
        }

    }



    private fun displayBMIResult(bmi: Float): Unit {

        var bmiLabel = ""
        var bmiDescription = ""


        when {
            bmi < 18.5 -> {
                bmiLabel = getString(R.string.bmiUnder)
                bmiDescription = getString(R.string.bmiUnderDesc)
            }

            bmi in 18.5..24.9 -> {
                bmiLabel = getString(R.string.bmiOk)
                bmiDescription = getString(R.string.bmiOkDesc)
            }

            bmi in 25.0.. 29.9 -> {
                bmiLabel = getString(R.string.bmiOver)
                bmiDescription = getString(R.string.bmiOverDesc)
            }

            bmi >= 30.0 -> {
                bmiLabel = getString(R.string.bmiObese)
                bmiDescription = getString(R.string.bmiObeseDesc)
            }
        }


        binding.resultTv.text = getString(R.string.resultTv,bmi,bmiLabel,bmiDescription)

    }
}