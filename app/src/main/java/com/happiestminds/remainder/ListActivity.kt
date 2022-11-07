package com.happiestminds.remainder

import android.icu.text.CaseMap.Title
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.Date
import android.widget.ArrayAdapter
import android.widget.Toast

class ListActivity : AppCompatActivity() {

    val remainderList = mutableListOf<Remind>()
    lateinit var adapter: ArrayAdapter<Remind>
    lateinit var listViewRemainder: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        listViewRemainder = findViewById(R.id.lv)

        //var list = listRemainder

        //val adapter = ArrayAdapter<Remainder>(this, android.R.layout.simple_list_item_1,list)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, remainderList)
        listViewRemainder.adapter = adapter

        listViewRemainder.setOnItemClickListener { adapterView, view, index, l ->
           // val selectedList = listRemainder[index]
            val selectedList = remainderList[index]
            //listRemainder.removeAt(index)


            var dlg =MyDialog()
            val dataBundle = Bundle()
            dlg.isCancelable = false

            dataBundle.putString("msg","Do you want to delete")
            dataBundle.putString("title", selectedList.title)
            dataBundle.putString("description",selectedList.description)
            dataBundle.putString("date",selectedList.date)
            dataBundle.putString("time",selectedList.time)

            dlg.arguments = dataBundle

            dlg.show(supportFragmentManager, null)
            remainderList.removeAt(index)

        }

    }

    override fun onResume() {
        super.onResume()
        setupData()
    }

    private fun setupData() {
        val cursor = DBWrapper(this).getAllRemainders()
        if (cursor.count > 0){
            val idx_title = cursor.getColumnIndexOrThrow(DBHelper.CLM_TITLE)
            val idx_description = cursor.getColumnIndexOrThrow(DBHelper.CLM_DESC)
            val idx_date = cursor.getColumnIndexOrThrow(DBHelper.CLM_DATE)
            val idx_time = cursor.getColumnIndexOrThrow(DBHelper.CLM_TIME)
            cursor.moveToFirst()
            remainderList.clear()

            do{
                val title = cursor.getString(idx_title)
                val description = cursor.getString(idx_description)
                val date = cursor.getString(idx_date)
                val time = cursor.getString(idx_time)

                val rem = Remind(title, description, date, time)
                remainderList.add(rem)
            }while (cursor.moveToNext())

            adapter.notifyDataSetChanged()

            Log.d("ListActivity", "List : $remainderList")
            Toast.makeText(this, "Found : ${remainderList.count()}",
                Toast.LENGTH_LONG).show()
        }
    }

}