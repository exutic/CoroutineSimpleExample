package com.example.coroutinesimpleexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViews()
        button.setOnClickListener(View.OnClickListener {

        })
    }

    fun findViews() {
        button = findViewById(R.id.btnClick)
    }


    private suspend fun getResult1FromApi() :String
    {


        return "Result_1"
    }
}