package com.example.calculator2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.calculator2.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var plusMinusCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        for (i in binding.numbers.referencedIds.indices) {
            findViewById<Button>(binding.numbers.referencedIds[i]).setOnClickListener() {
                val number = (it as Button).text
                binding.resultView.append(number)
            }
        }

        for (i in binding.mathOperation.referencedIds.indices) {
            findViewById<Button>(binding.mathOperation.referencedIds[i]).setOnClickListener() {
                val oper = (it as Button).text
                    if (listOf("0", "1", "2","3","4","5","6","7","8","9").any { it == binding.resultView.text.last().toString() } ) {
                        binding.resultView.append(oper)
                        plusMinusCount = 0
                    }
            }
}
            binding.ACbtn.setOnClickListener() {
                binding.resultView.text = ""
            }

            binding.pointBtn.setOnClickListener() {
                if (binding.resultView.text.isNotEmpty()) {
                    if (binding.resultView.text.last() != '.' ) {
                        binding.resultView.append(".")
                    }
                } else binding.resultView.append("0.")
                }

            binding.PlusMinusBtn.setOnClickListener() {
                plusMinusCount++
                val str = binding.resultView.text
                if (plusMinusCount % 2 == 1) {
                    binding.resultView.append("-")
                }
                else binding.resultView.text = str.substring(0, str.length-1)

            }

            binding.percentBtn.setOnClickListener(){
                val str = ExpressionBuilder(binding.resultView.text.toString()).build()
                val d = str.evaluate()
                var c = d.toDouble()
                var a = c / 100
                c = a
               binding.resultView.text = c.toString()
            }

            binding.equalBtn.setOnClickListener() {
                try{
                    val expr = ExpressionBuilder(binding.resultView.text.toString()).build()
                    val res = expr.evaluate()
                    val longRes = res.toLong()
                    if (res == longRes.toDouble())
                       binding.resultView.text = longRes.toString()
                    else binding.resultView.text = res.toString()

                } catch(e:Exception){
                    Log.d("Ошибка","сообщение: ${e.message}")
                    binding.resultView.text = "ОШИБКА"
                }
            }
        }
    }











