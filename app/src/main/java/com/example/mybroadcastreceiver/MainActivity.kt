package com.example.mybroadcastreceiver

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var myStorage: MyStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        myStorage = MyStorage(this)

        findViewById<Button>(R.id.btn_clear_data).setOnClickListener {
            myStorage.clearData()
            showData()
        }

    }

    override fun onStart() {
        super.onStart()
        showData()
    }

    private fun showData() {
        val data = myStorage.getCurrentData()
        findViewById<TextView>(R.id.tv_info).text = data.toString()
    }
}