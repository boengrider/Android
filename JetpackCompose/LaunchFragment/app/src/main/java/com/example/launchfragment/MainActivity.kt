package com.example.launchfragment

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.example.launchfragment.databinding.ActivityMainBinding

/**
 * Approach B
 */
class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var listenersList: ArrayList<(View) -> Unit> = arrayListOf()

    private var textFragment: FragmentContainerView? = null
    private var imageFragment: FragmentContainerView? = null
    private var hideTextFragmentButton: Button? = null
    private var hideImageFragmentButton: Button? = null
    private var hideBoth: Button? = null
    private val callbacksMap: MutableMap<Int, (() -> Unit)> = mutableMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        textFragment = binding.fragmentContainerView
        imageFragment = binding.imageFragment
        hideTextFragmentButton = binding.hideTextFragmentButton
        hideImageFragmentButton = binding.hideImageFragmentButton
        hideBoth = binding.hideBoth

        callbacksMap[hideImageFragmentButton!!.id] = {
            if(imageFragment!!.isVisible) {
                imageFragment!!.visibility = INVISIBLE
            } else {
                imageFragment!!.visibility = VISIBLE
            }
        }

        callbacksMap[hideTextFragmentButton!!.id] = {
            if(textFragment!!.isVisible) {
                textFragment!!.visibility = INVISIBLE
            } else {
                textFragment!!.visibility = VISIBLE
            }
        }

        hideImageFragmentButton!!.setOnClickListener {
            callbacksMap[it.id]?.invoke()
        }

        hideTextFragmentButton!!.setOnClickListener {
            callbacksMap[it.id]?.invoke()
        }




    }
}


/**
//Approach A
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    private var textFragment: FragmentContainerView? = null
    private var imageFragment: FragmentContainerView? = null
    private var hideTextFragmentButton: Button? = null
    private var hideImageFragmentButton: Button? = null
    private var hideBoth: Button? = null
    private var imageView: ImageView? = null


    //Override a method from View interface we implement
    override fun onClick(v: View?) {
        when(v) {
            hideTextFragmentButton -> {
                Toast.makeText(this, "Hide text fragment button pressed", Toast.LENGTH_LONG).show()

                if(textFragment!!.isVisible) {
                    textFragment!!.visibility = INVISIBLE
                } else {
                    textFragment!!.visibility = VISIBLE
                }

            }

            hideImageFragmentButton -> {
                Toast.makeText(this,"Hide image fragment button pressed",Toast.LENGTH_LONG).show()

                if(imageFragment!!.isVisible) {
                    imageFragment!!.visibility = INVISIBLE
                } else {
                    imageFragment!!.visibility = VISIBLE
                }
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        textFragment = binding.fragmentContainerView
        imageFragment = binding.imageFragment
        hideTextFragmentButton = binding.hideTextFragmentButton
        hideImageFragmentButton = binding.hideImageFragmentButton
        hideBoth = binding.hideBoth

        hideImageFragmentButton!!.setOnClickListener(this)
        hideTextFragmentButton!!.setOnClickListener(this)





    }


}

fun myOnClickListener(c: Context, v: View) {
    Toast.makeText(c,"myOnClickListener() invoked by view ${v.id}", Toast.LENGTH_SHORT).show()
}
 **/