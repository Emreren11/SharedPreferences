package com.emre.storingdata

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.emre.storingdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //SharedPreferences -> xml dosyası oluşturur küçük verileri depolar. Bir anahtar kelime ile kontrol edilir
        sharedPref = getSharedPreferences(
            "com.emre.storingdata", MODE_PRIVATE) //(xml dosya ismi(değişebilir), Private -> sadece uygulamada kullanılır)
        val userAgePref = sharedPref.getInt("age",0)
        if (userAgePref != 0) {
            binding.textView.text = "Your Age: ${userAgePref}"
        } else {
            binding.textView.text = "Your Age: "
        }
    }

    fun save(view: View) {

        val myAge = binding.ageText.text.toString().toIntOrNull()
        if (myAge != null) {
            binding.textView.text = "Your Age: ${myAge}"
            sharedPref.edit().putInt("age", myAge).apply() // (anahtar kelime, depolanacak değer)
        }
    }

    fun delete(view: View) {

        sharedPref.edit().remove("age").apply()
        binding.textView.text = "Your Age: "
    }
}