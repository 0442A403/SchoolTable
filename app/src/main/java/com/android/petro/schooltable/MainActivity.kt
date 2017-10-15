package com.android.petro.schooltable

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import org.jetbrains.anko.setContentView
import java.util.*


class MainActivity : AppCompatActivity(), OnDaySelectedListener {

    private val days: ArrayList<Day> = ArrayList()
    private val tableName = "Lessons"
    private var dayHashMap: HashMap<String, LinearLayout>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val schoolTableInterface = UserInterface(this)
        schoolTableInterface.setContentView(this)
        dayHashMap = schoolTableInterface.getDictionary()
        val mainLayout = schoolTableInterface.getLayout()

        if (getSharedPreferences("First open", Context.MODE_PRIVATE).getBoolean("First open", true)) {
            Snackbar.make(mainLayout, "Удерживайте, чтобы изменить", Snackbar.LENGTH_LONG).show()
            getSharedPreferences("First open", Context.MODE_PRIVATE).edit().putBoolean("First open", false).apply()
        }

        val lessonsDatabase = Lessons(this, tableName)
        val query = "SELECT * FROM $tableName"
        val cursor = lessonsDatabase.readableDatabase.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndex("day"))
//                val id = resources.getIdentifier(name, "id", packageName)
                val layout = dayHashMap!![name]
                days.add(Day(
                        DayType.getDayByName(name),
                        layout!!,
                        mainLayout,
                        cursor,
                        this,
                        this,
                        tableName,
                        lessonsDatabase)
                )
            } while (cursor.moveToNext())
        }
        else {
            throw Exception()
        }
        cursor.close()

        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_WEEK)
        val calendarDays = arrayOf(
                Calendar.MONDAY,
                Calendar.TUESDAY,
                Calendar.WEDNESDAY,
                Calendar.THURSDAY,
                Calendar.FRIDAY,
                Calendar.SATURDAY
        )
        if (calendarDays.contains(day) && days[calendarDays.indexOf(day)].isSchoolTime(calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE)))
            days[calendarDays.indexOf(day)].show()
        else
            days[(calendarDays.indexOf(day) + 1) % 6].show()
    }

    override fun onDaySelected() {
        for (day in days)
            day.hide()
    }
}
