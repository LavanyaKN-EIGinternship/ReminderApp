package com.happiestminds.remainder

import android.content.ContentValues
import android.content.Context
import android.database.Cursor

class DBWrapper(ctx: Context) {

    val helper = DBHelper(ctx)
    val db = helper.writableDatabase

    fun addRemainder(rem : Remind):  Boolean{
        //insert
        val values = ContentValues()
        values.put(DBHelper.CLM_TITLE, rem.title)
        values.put(DBHelper.CLM_DESC, rem.description)
        values.put(DBHelper.CLM_DATE, rem.date)
        values.put(DBHelper.CLM_TIME, rem.time)

        val rowid = db.insert(DBHelper.TABLE_NAME, null,values)
        if (rowid.toInt() == -1){
            return false
        }
        return true
    }

    fun getAllRemainders():Cursor{
        //query
        val clms = arrayOf(DBHelper.CLM_TITLE, DBHelper.CLM_DESC, DBHelper.CLM_DATE, DBHelper.CLM_TIME)
        return db.query(DBHelper.TABLE_NAME, clms,
            null, null, null, null, null)
    }

    fun deleteRemainder(rem: Remind){
        db.delete(DBHelper.TABLE_NAME, "${DBHelper.CLM_TITLE} = ?",
            arrayOf(rem.title))
    }
}

data class Remind(val title: String, val description: String, val date: String, val time : String){
    override fun toString(): String {
        return """
            Title : $title
            Description : $description
            Date :  $date
            Time: $time
        """.trimIndent()
    }
}