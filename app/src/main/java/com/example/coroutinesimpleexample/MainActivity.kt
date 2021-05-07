package com.example.coroutinesimpleexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val RESULT_1 = "Result_1"
    private val RESULT_2 = "Result_2"
    lateinit var button: Button
    lateinit var tv_result: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViews()
        button.setOnClickListener(View.OnClickListener {
            //IO, Main, Default
            CoroutineScope(IO).launch {
                fakeApiRequest()
            }
        })
    }


    private fun setNewText(input: String) {
        val newText = tv_result.text.toString() + "\n$input"
        tv_result.text = newText
    }

    private suspend fun setTextOnMainThread(input: String) {
        withContext(Main)
        {
            setNewText(input)
        }
    }

    private suspend fun fakeApiRequest() {
        val result1 = getResult1FromApi()
        setTextOnMainThread(result1)

        println("Debug :$result1")

        val result2 = getResult2FromApi()
        setTextOnMainThread(result2)
    }

    private suspend fun getResult1FromApi(): String {
        logThread("getResult1FromApi")
        delay(1000)

        return RESULT_1
    }

    private suspend fun getResult2FromApi(): String {
        logThread("getResult2FromApi")
        delay(1000)

        return RESULT_2
    }

    private fun logThread(methodName: String) {
        println("Debug ${methodName}: ${Thread.currentThread().name}")
    }
    fun findViews() {
        button = findViewById(R.id.btn_click)
        tv_result = findViewById(R.id.tv_result)
    }

}