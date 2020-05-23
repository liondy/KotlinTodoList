package com.liondy.kotlintodolist

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var listView: ListView? = null
    private var text: EditText? = null
    private var btn_add: ImageButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById<View>(R.id.lstView) as ListView
        text = findViewById<View>(R.id.text) as EditText
        btn_add = findViewById<View>(R.id.btn_add) as ImageButton
        val listAdapter = ListAdapter(this, this)
        listView!!.adapter = listAdapter
        btn_add!!.setOnClickListener { v ->
            val imm = (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
            imm.hideSoftInputFromWindow(v.windowToken, 0)
            val todo = text!!.text.toString()
            if (todo != "") listAdapter.add(todo)
            text!!.setText("")
        }
    }
}
