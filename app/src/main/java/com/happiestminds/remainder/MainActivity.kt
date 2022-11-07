package com.happiestminds.remainder

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var backCount = 0
    override fun onBackPressed() {
        backCount++

        if (backCount == 2) {
            //backCount = 0
            super.onBackPressed() // activity is invisible
        }else {
            Toast.makeText(this,
                "Pls press 'Back' again to exit",
                Toast.LENGTH_LONG ).show()
        }
    }
    fun buttonClick(view: View) {
        when (view.id) {
            R.id.addB -> {
                val addIntent = Intent(this, AddActivity().javaClass)
                startActivity(addIntent)
            }
            R.id.listB -> {
//                showToast("Clicked Button: ListView Button")


                val listIntent = Intent(this, ListActivity().javaClass)
                startActivity(listIntent)


            }
        }
    }

    override fun onResume() {
        if (checkSelfPermission(Manifest.permission.READ_CALENDAR)!=PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(Manifest.permission.READ_CALENDAR,Manifest.permission.WRITE_CALENDAR),1)
        }
        super.onResume()
    }
}