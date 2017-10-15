package com.android.petro.schooltable

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

/**
 * Main database
 * Created by petro on 28.09.2017.
 */
class Lessons(context: Context,
              private val name: String): SQLiteOpenHelper(context, name, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var query = "CREATE TABLE IF NOT EXISTS $name" +
                "(day TEXT NOT NULL," +
                "Lesson0 TEXT," +
                "Lesson1 TEXT," +
                "Lesson2 TEXT," +
                "Lesson3 TEXT," +
                "Lesson4 TEXT," +
                "Lesson5 TEXT," +
                "Lesson6 TEXT," +
                "Lesson7 TEXT," +
                "Lesson8 TEXT," +
                "Lesson0time TEXT," +
                "Lesson1time TEXT," +
                "Lesson2time TEXT," +
                "Lesson3time TEXT," +
                "Lesson4time TEXT," +
                "Lesson5time TEXT," +
                "Lesson6time TEXT," +
                "Lesson7time TEXT," +
                "Lesson8time TEXT," +
                "Lesson0classroom TEXT," +
                "Lesson1classroom TEXT," +
                "Lesson2classroom TEXT," +
                "Lesson3classroom TEXT," +
                "Lesson4classroom TEXT," +
                "Lesson5classroom TEXT," +
                "Lesson6classroom TEXT," +
                "Lesson7classroom TEXT," +
                "Lesson8classroom TEXT);"
        db?.execSQL(query)
        query = "INSERT INTO $name VALUES(" +
                "'Monday'," +
                "'История'," +
                "'Алгебра'," +
                "'Алгебра'," +
                "'История'," +
                "'Биология'," +
                "'Информатика'," +
                "'Информатика'," +
                "'Английский язык'," +
                "''," +
                "'8:00 - 8:40'," +
                "'8:50 - 9:35'," +
                "'9:40 - 10:25'," +
                "'10:40 - 11:25'," +
                "'11:40 - 12:25'," +
                "'12:40 - 13:25'," +
                "'13:40 - 14:25'," +
                "'14:30 - 15:15'," +
                "'15:20 - 16:05'," +
                "'28'," +
                "'19'," +
                "'19'," +
                "'28'," +
                "'3'," +
                "'23/33'," +
                "'23/33'," +
                "'5/26'," +
                "'');"
        db?.execSQL(query)
        query = "INSERT INTO $name VALUES(" +
                "'Tuesday'," +
                "'Математика (доп.)'," +
                "'Русский язык'," +
                "'Литература'," +
                "'Физика'," +
                "'Физика'," +
                "'Геометрия'," +
                "'Химия'," +
                "'География'," +
                "''," +
                "'8:00 - 8:40'," +
                "'8:50 - 9:35'," +
                "'9:40 - 10:25'," +
                "'10:40 - 11:25'," +
                "'11:40 - 12:25'," +
                "'12:40 - 13:25'," +
                "'13:40 - 14:25'," +
                "'14:30 - 15:15'," +
                "'15:20 - 16:05'," +
                "'19'," +
                "'18'," +
                "'18'," +
                "'32'," +
                "'32'," +
                "'19'," +
                "'37'," +
                "'31'," +
                "'');"
        db?.execSQL(query)
        query = "INSERT INTO $name VALUES(" +
                "'Wednesday'," +
                "'Физическая культура'," +
                "'Физическая культура'," +
                "'Английский язык'," +
                "'Русский язык'," +
                "'Литература'," +
                "'Математика (платная)'," +
                "''," +
                "''," +
                "''," +
                "'8:00 - 8:40'," +
                "'8:50 - 9:35'," +
                "'9:40 - 10:25'," +
                "'10:40 - 11:25'," +
                "'11:40 - 12:25'," +
                "'12:40 - 13:25'," +
                "'13:40 - 14:25'," +
                "'14:30 - 15:15'," +
                "'15:20 - 16:05'," +
                "'36'," +
                "'36'," +
                "'5/26'," +
                "'32'," +
                "'32'," +
                "'19'," +
                "''," +
                "''," +
                "'');"
        db?.execSQL(query)
        query = "INSERT INTO $name VALUES(" +
                "'Thursday'," +
                "'Основы безопасной жизнедеятельности'," +
                "'Геометрия'," +
                "'Геометрия'," +
                "'Информатика'," +
                "'Информатика'," +
                "'Физика'," +
                "'Физика'," +
                "''," +
                "''," +
                "'8:00 - 8:40'," +
                "'8:50 - 9:35'," +
                "'9:40 - 10:25'," +
                "'10:40 - 11:25'," +
                "'11:40 - 12:25'," +
                "'12:40 - 13:25'," +
                "'13:40 - 14:25'," +
                "'14:30 - 15:15'," +
                "'15:20 - 16:05'," +
                "'2'," +
                "'19'," +
                "'19'," +
                "'23/33'," +
                "'23/33'," +
                "'32'," +
                "'32'," +
                "''," +
                "'');"
        db?.execSQL(query)
        query = "INSERT INTO $name VALUES(" +
                "'Friday'," +
                "'Классный час'," +
                "'Алгебра'," +
                "'Алгебра'," +
                "'Русский язык'," +
                "'Литература'," +
                "'Физическая культура'," +
                "'Математика (Дегтярев)'," +
                "'Математика (платная)'," +
                "''," +
                "'8:15 - 8:40'," +
                "'8:50 - 9:35'," +
                "'9:40 - 10:25'," +
                "'10:40 - 11:25'," +
                "'11:40 - 12:25'," +
                "'12:40 - 13:25'," +
                "'13:40 - 14:25'," +
                "'14:30 - 15:15'," +
                "'15:20 - 16:05'," +
                "'37'," +
                "'19'," +
                "'19'," +
                "'30'," +
                "'30'," +
                "'трен.'," +
                "'21'," +
                "'19'," +
                "'');"
        db?.execSQL(query)
        query = "INSERT INTO $name VALUES(" +
                "'Saturday'," +
                "'Английский язык'," +
                "'Обществознание'," +
                "'Физика'," +
                "'Астрономия'," +
                "'Обществознание'," +
                "''," +
                "''," +
                "''," +
                "''," +
                "'8:00 - 8:40'," +
                "'8:50 - 9:30'," +
                "'9:35 - 10:15'," +
                "'10:25 - 11:05'," +
                "'11:15 - 11:55'," +
                "'12:05 - 12:45'," +
                "'12:55 - 13:35'," +
                "'13:40 - 14:20'," +
                "'14:25 - 15:05'," +
                "'5/26'," +
                "'28'," +
                "'32'," +
                "'32'," +
                "'28'," +
                "''," +
                "''," +
                "''," +
                "'');"
        db?.execSQL(query)
        Log.d("SchoolTableDebug", "1111")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}