package com.example.l_shared_preference

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.l_shared_preference.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {

//    declare
    var count=0
    var send:String? = null
    var message:String? = null
    var isChecked:Boolean? = null
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        onClick btnCount
        binding.btnCount.setOnClickListener {
            count++
            binding.btnCount.text = count.toString()
        }
    }

//    save state in onPause
    override fun onPause() {
        super.onPause()
        saveData()
    }

//    restore state in onResume
    override fun onResume() {
        super.onResume()
        restoreData()
    }

    private fun restoreData() {
        sharedPreferences = this.getSharedPreferences("saveData", Context.MODE_PRIVATE)
        send = sharedPreferences.getString("key_send", null)
        message = sharedPreferences.getString("key_message", null)
        count = sharedPreferences.getInt("key_count", 0)
        isChecked = sharedPreferences.getBoolean("key_remember", false)

//        render on screen
        binding.edtSend.setText(send)
        binding.edtMessage.setText(message)
        binding.btnCount.text = count.toString()
        binding.chkRemember.isChecked = isChecked!!
    }

    private fun saveData() {
//        save into file
        sharedPreferences = this.getSharedPreferences("saveData", Context.MODE_PRIVATE)
//        get data
        send = binding.edtSend.text.toString()
        message = binding.edtMessage.text.toString()
        isChecked = binding.chkRemember.isChecked
//        save information
        var editor = sharedPreferences.edit()
        editor.putString("key_send", send)
        editor.putString("key_message", message)
        editor.putInt("key_count", count)
        editor.putBoolean("key_remember", isChecked!!)
        editor.apply()
        Toast.makeText(applicationContext,"Data saved",Toast.LENGTH_SHORT).show()

    }
}