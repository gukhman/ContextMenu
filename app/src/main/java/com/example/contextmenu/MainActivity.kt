package com.example.contextmenu

import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    //объявляем переменные
    private lateinit var buttonRandom: Button
    private lateinit var inputText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //инициализируем
        buttonRandom = findViewById(R.id.buttonRandom)
        inputText = findViewById(R.id.inputText)
        registerForContextMenu(inputText)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuColor -> {
                val number = inputText.text.toString().toIntOrNull()
                when (number) {
                    null -> {
                        inputText.setText(getString(R.string.defaultValue))
                        inputText.setTextColor(resources.getColor(R.color.blue))
                    }

                    in 1..10 -> inputText.setTextColor(resources.getColor(R.color.red))
                    in 11..20 -> inputText.setTextColor(resources.getColor(R.color.orange))
                    in 21..30 -> inputText.setTextColor(resources.getColor(R.color.yellow))
                    in 31..40 -> inputText.setTextColor(resources.getColor(R.color.green))
                    in 41..50 -> inputText.setTextColor(resources.getColor(R.color.blue))

                    else -> {
                        inputText.setText(getString(R.string.defaultValue))
                        inputText.setTextColor(resources.getColor(R.color.blue))
                    }
                }
            }

            R.id.menuExit -> {
                finish()
            }

        }
        return true
    }

    fun random(view: View) {
        inputText.setTextColor(resources.getColor(R.color.black))
        val rand = Random.nextInt(1, 51).toString()
        inputText.setText(rand)
    }
}