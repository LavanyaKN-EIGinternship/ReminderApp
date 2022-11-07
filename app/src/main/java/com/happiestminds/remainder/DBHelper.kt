package com.happiestminds.remainder

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context):
    SQLiteOpenHelper(context, "remainder.db",null,1) {

    override fun onCreate(db: SQLiteDatabase?) {
        //table creation to be done(executed with db not presented)
        db?.execSQL(TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //executed when version mismatch
        //drop table, create new table, modify schema of existing tables
    }

    companion object{
    const val TABLE_NAME = "RemainderData"
        const val CLM_TITLE = "title"
        const val CLM_DESC = "description"
        const val CLM_DATE = "date"
        const val CLM_TIME = "time"
        const val TABLE_QUERY = "create table $TABLE_NAME ($CLM_TITLE text PRIMARY KEY, $CLM_DESC text, $CLM_DATE text, $CLM_TIME text)"
    }

}