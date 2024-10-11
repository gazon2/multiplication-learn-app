package com.example.lr2
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAllNumbers = findViewById<Button>(R.id.btn_all_numbers)
        val btnSelectedNumber = findViewById<Button>(R.id.btn_selected_number)
        val editTextNumber = findViewById<EditText>(R.id.editTextNumber)

        btnAllNumbers.setOnClickListener {
            val intent = Intent(this, LearnActivity::class.java)
            intent.putExtra("mode", "all")
            startActivity(intent)
        }

        btnSelectedNumber.setOnClickListener {
            val selectedNumberStr = editTextNumber.text.toString()


            if (selectedNumberStr.isEmpty() || selectedNumberStr.toInt() !in 2..9) {
                Toast.makeText(this, "Введите число от 2 до 9", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, LearnActivity::class.java)
                intent.putExtra("mode", "selected")
                intent.putExtra("selectedNumber", selectedNumberStr.toInt())
                startActivity(intent)
            }
        }
    }
}
