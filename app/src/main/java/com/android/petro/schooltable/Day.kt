package com.android.petro.schooltable

import android.app.TimePickerDialog
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import android.support.design.widget.Snackbar
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationSet
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import com.transitionseverywhere.Fade
import com.transitionseverywhere.TransitionManager
import com.transitionseverywhere.TransitionSet
import com.transitionseverywhere.extra.Scale
import kotlinx.android.synthetic.main.day.view.*
import kotlinx.android.synthetic.main.lesson.view.*
import kotlinx.android.synthetic.main.lesson__edit_text.view.*
import org.jetbrains.anko.timePicker
import org.jetbrains.anko.verticalLayout

/**
 * Created by petro on 02.10.2017.
 * Class provides day's functional
 */
class Day(private val day: DayType,
          private val layout: ViewGroup,
          private val globalLayout: ViewGroup,
          cursor: Cursor,
          private val context: Context,
          private val callback: OnDaySelectedListener,
          private val tableName: String,
          private val database: SQLiteOpenHelper) {
    private var visible = false
    private var editing = false
    private var endTime: Int? = null
    init {
        layout.dayTitle.text = day.dayName
        for (i in 0..8) {
            val id = context.resources.getIdentifier("lesson$i", "id", context.packageName)
            val lessonLayout = layout.findViewById(id) as ViewGroup
            lessonLayout.lessonIndex.text = "$i. "
            val lessonName = cursor.getString(cursor.getColumnIndex("Lesson$i"))
            if (!lessonName.isEmpty()) {
                val timeArray = cursor.getString(cursor.getColumnIndex("Lesson${i}EndTime")).split(':')
                val time = timeArray[0].toInt() * 60 + timeArray[1].toInt()
                if (endTime == null || endTime!! < time)
                    endTime = time
            }
            val startLessonTime = cursor.getString(cursor.getColumnIndex("Lesson${i}StartTime"))
            val endLessonTime = cursor.getString(cursor.getColumnIndex("Lesson${i}EndTime"))
            val lessonClassroom = cursor.getString(cursor.getColumnIndex("Lesson${i}classroom"))
            val editTextId = context.resources.getIdentifier("lesson${i}__edit_text", "id", context.packageName)
            val lessonLayoutEditText = layout.findViewById(editTextId) as ViewGroup
            lessonLayoutEditText.lessonIndex_EditText.text = "$i. "
            lessonLayoutEditText.lesson_EditText.setText(lessonName)
            lessonLayoutEditText.lessonStartTime_EditText.text = startLessonTime
            lessonLayoutEditText.lessonEndTime_EditText.text = endLessonTime
            lessonLayoutEditText.lessonsClassroom_EditText.setText(lessonClassroom)
            matchTextViews()
            lessonLayoutEditText.lessonStartTime_EditText.setOnClickListener {
                Log.d("SchoolTableDebug", "in lessonsTime's onClickListener1")
                with(context) {
                    Log.d("SchoolTableDebug", "in lessonsTime's onClickListener2")
                    val timeDialog = TimePickerDialog(
                            context,
                            { _, hours: Int, minutes: Int ->
                                lessonLayoutEditText.lessonStartTime_EditText.text = "$hours:$minutes"
                            }, 24, 60, true)
                    timeDialog.show()
                }
            }
            lessonLayoutEditText.lessonEndTime_EditText.setOnClickListener {
                Log.d("SchoolTableDebug", "in lessonsTime's onClickListener1")
                with(context) {
                    Log.d("SchoolTableDebug", "in lessonsTime's onClickListener2")
                    val timeDialog = TimePickerDialog(
                            context,
                            { _, hours: Int, minutes: Int ->
                                lessonLayoutEditText.lessonEndTime_EditText.text = "$hours:$minutes"
                            }, 24, 60, true)
                    timeDialog.show()
                }
            }
        }
        layout.dayTitle_Container.setOnClickListener {
            swapVisible()
        }
        layout.arrow.setOnClickListener {
            swapVisible()
        }
        layout.dayTitle_Container.setOnLongClickListener {
            if (!editing && !visible)
                openEditing()
            else if (visible)
                swapVisible()
            return@setOnLongClickListener true
        }
        layout.arrow.setOnLongClickListener {
            if (!editing && !visible)
                openEditing()
            else if (visible)
                swapVisible()
            return@setOnLongClickListener true
        }

    }

    private fun matchTextViews() {
        for (i in 0..8) {
            val id = context.resources.getIdentifier("lesson$i", "id", context.packageName)
            val lessonLayout = layout.findViewById(id) as ViewGroup
            val editTextId = context.resources.getIdentifier("lesson${i}__edit_text", "id", context.packageName)
            val lessonLayoutEditText = layout.findViewById(editTextId) as ViewGroup
            if (lessonLayoutEditText.lesson_EditText.length() == 0)
                lessonLayout.visibility = View.GONE
            else
                lessonLayout.visibility = View.VISIBLE
            lessonLayout.lesson.text = lessonLayoutEditText.lesson_EditText.text
            lessonLayout.lessonStartTime.text = lessonLayoutEditText.lessonStartTime_EditText.text
            lessonLayout.lessonEndTime.text = lessonLayoutEditText.lessonEndTime_EditText.text
            lessonLayout.lessonsClassroom.text = lessonLayoutEditText.lessonsClassroom_EditText.text
        }
    }

    private fun swapVisible() {
        if (!visible)
            show()
        else
            hide()
    }

    fun show() {
        callback.onDaySelected()

        val set = TransitionSet()
                .addTransition(Scale(0.7f))
                .addTransition(Fade())
                .setDuration(200L)
                .setInterpolator(LinearOutSlowInInterpolator())
        TransitionManager.beginDelayedTransition(globalLayout, set)
        layout.lessons.visibility = View.VISIBLE

        rotateArrow(true)

        callback.onDaySelected()

        visible = true
    }

    fun hide() {
        if (visible) {
            if (editing)
                hideWithSaving()
            else
                hideWithoutSaving()
        }
    }

    private fun hideWithoutSaving() {
        val set = TransitionSet()
                .addTransition(Scale(0.7f))
                .addTransition(Fade())
                .setDuration(200L)
                .setInterpolator(LinearOutSlowInInterpolator())
        TransitionManager.beginDelayedTransition(globalLayout, set)
        layout.lessons.visibility = View.GONE

        rotateArrow(false)

        visible = false
    }

    private fun openEditing() {
        callback.onDaySelected()

        val set = TransitionSet()
                .addTransition(Scale(0.7f))
                .addTransition(Fade())
                .setDuration(200L)
                .setInterpolator(LinearOutSlowInInterpolator())
        TransitionManager.beginDelayedTransition(globalLayout, set)
        layout.lessons_EditText.visibility = View.VISIBLE

        rotateArrow(true)

        visible = true
        editing = true
    }

    private fun hideWithSaving() {
        val set = TransitionSet()
                .addTransition(Scale(0.7f))
                .addTransition(Fade())
                .setDuration(200L)
                .setInterpolator(LinearOutSlowInInterpolator())
        TransitionManager.beginDelayedTransition(globalLayout, set)
        layout.lessons_EditText.visibility = View.GONE

        rotateArrow(false)

        val query = StringBuilder("UPDATE $tableName SET ")
        for (i in 0..8) {
            val editTextId = context.resources.getIdentifier("lesson${i}__edit_text", "id", context.packageName)
            val lessonLayoutEditText = layout.findViewById(editTextId) as ViewGroup
            query.append("Lesson$i = '${lessonLayoutEditText.lesson_EditText.text}', ")
            query.append("Lesson${i}StartTime = '${lessonLayoutEditText.lessonStartTime_EditText.text}', ")
            query.append("Lesson${i}EndTime = '${lessonLayoutEditText.lessonEndTime_EditText.text}',")
            query.append("Lesson${i}classroom = '${lessonLayoutEditText.lessonsClassroom_EditText.text}', ")
        }
        query.delete(query.length - 2, query.length - 1)
        query.append("WHERE day = '${day.name.substring(0, 1)}${day.name.substring(1).toLowerCase()}';")
        Log.d("SchoolTable", query.toString())
        database.writableDatabase.execSQL(query.toString())

        matchTextViews()

        Snackbar.make(globalLayout, "Сохранено", Snackbar.LENGTH_SHORT).show()

        visible = false
        editing = false
    }

    private fun rotateArrow(downToUp: Boolean) {
        val arrow = layout.arrow
        val animSet = AnimationSet(true)
        animSet.interpolator = DecelerateInterpolator()
        animSet.fillAfter = true
        animSet.isFillEnabled = true
        val animRotate =
                if (downToUp)
                    RotateAnimation(0.0f, -180.0f,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f)
                else
                    RotateAnimation(-180.0f, 0.0f,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f)
        animRotate.duration = 200
        animRotate.fillAfter = true
        animSet.addAnimation(animRotate)
        arrow.startAnimation(animSet)
    }

    fun isSchoolTime(time: Int): Boolean = !(endTime != null && endTime!! <= time)
}