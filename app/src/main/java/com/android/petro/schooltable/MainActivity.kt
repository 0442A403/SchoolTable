package com.android.petro.schooltable

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationSet
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import com.transitionseverywhere.Fade
import com.transitionseverywhere.TransitionManager
import com.transitionseverywhere.TransitionSet
import com.transitionseverywhere.extra.Scale
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.day.view.*
import kotlinx.android.synthetic.main.lesson.view.*
import kotlinx.android.synthetic.main.lesson__edit_text.view.*
import java.util.*


class MainActivity : AppCompatActivity(), OnDaySelectedListener {

    private val days: ArrayList<Day> = ArrayList()
    private val tableName = "Lessons"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (getSharedPreferences("First open", Context.MODE_PRIVATE).getBoolean("First open", true)) {
            Snackbar.make(scrollContainer, "Удерживайте, чтобы изменить", Snackbar.LENGTH_LONG).show()
            getSharedPreferences("First open", Context.MODE_PRIVATE).edit().putBoolean("First open", false).apply()
        }

        val lessonsDatabase = Lessons(this, tableName)
        val query = "SELECT * FROM $tableName"
        val cursor = lessonsDatabase.readableDatabase.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndex("day"))
                val id = resources.getIdentifier(name, "id", packageName)
                val layout = findViewById(id) as ViewGroup
                days.add(Day(
                        DayType.getDayByName(name),
                        layout,
                        scrollContainer,
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
        if (calendarDays.contains(day))
            days[calendarDays.indexOf(day)].show()
    }

    override fun onDaySelected() {
        for (day in days)
            day.hide()
    }
}
