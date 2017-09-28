package com.android.petro.schooltable

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.util.Log
import android.view.View
import com.transitionseverywhere.*
import kotlinx.android.synthetic.main.activity_main.*
import com.transitionseverywhere.extra.Scale
import kotlinx.android.synthetic.main.monday.*
import android.view.animation.RotateAnimation
import android.view.animation.DecelerateInterpolator
import android.view.animation.AnimationSet
import kotlinx.android.synthetic.main.friday.*
import kotlinx.android.synthetic.main.saturday.*
import kotlinx.android.synthetic.main.thursday.*
import kotlinx.android.synthetic.main.tuesday.*
import kotlinx.android.synthetic.main.wednesday.*


class MainActivity : AppCompatActivity() {

    private val days = arrayOf(Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY, Day.FRIDAY, Day.SATURDAY)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        monday.setOnClickListener {
            Log.d("SchoolTableInformation", (mondayLessons.visibility == View.GONE).toString())
            swapDay(Day.MONDAY, mondayLessons.visibility == View.GONE)
        }
        tuesday.setOnClickListener {
            swapDay(Day.TUESDAY, tuesdayLessons.visibility == View.GONE)
        }
        wednesday.setOnClickListener {
            swapDay(Day.WEDNESDAY, wednesdayLessons.visibility == View.GONE)
        }
        thursday.setOnClickListener {
            swapDay(Day.THURSDAY, thursdayLessons.visibility == View.GONE)
        }
        friday.setOnClickListener {
            Log.d("SchoolTableDebug", "OnClickListener")
            swapDay(Day.FRIDAY, fridayLessons.visibility == View.GONE)
        }
        friday.setOnLongClickListener {
            Log.d("SchoolTableDebug", "OnLongClickListener")
            true
        }
        saturday.setOnClickListener {
            swapDay(Day.SATURDAY, saturdayLessons.visibility == View.GONE)
        }

    }

    private fun swapDay(day: Day, checked: Boolean) {
        for (curDay in days)
            if (curDay == day)
                changeDay(curDay, checked)
            else
                changeDay(curDay, false)
    }

    private fun changeDay(day: Day, checked: Boolean) {
        when(day) {
            Day.MONDAY    ->
                    changeMondayVisible(checked)
            Day.TUESDAY   ->
                    changeTuesdayVisible(checked)
            Day.WEDNESDAY ->
                    changeWednesdayVisible(checked)
            Day.THURSDAY  ->
                    changeThursdayVisible(checked)
            Day.FRIDAY    ->
                    changeFridayVisible(checked)
            Day.SATURDAY  ->
                    changeSaturdayVisible(checked)
        }
    }

    private fun changeSaturdayVisible(checked: Boolean) {
        if ((saturdayLessons.visibility == View.VISIBLE && checked)
                || (saturdayLessons.visibility == View.GONE && !checked))
            return

        val set = TransitionSet()
                .addTransition(Scale(0.7f))
                .addTransition(Fade())
                .setDuration(200L)
                .setInterpolator(LinearOutSlowInInterpolator())

        TransitionManager.beginDelayedTransition(scrollContainer, set)
        saturdayLessons.visibility =
                if (checked)
                    View.VISIBLE
                else
                    View.GONE

        val myImageView = arrow_Saturday

        val animSet = AnimationSet(true)
        animSet.interpolator = DecelerateInterpolator()
        animSet.fillAfter = true
        animSet.isFillEnabled = true

        val animRotate =
                if (checked)
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

        myImageView.startAnimation(animSet)
    }

    private fun changeFridayVisible(checked: Boolean) {
        if ((fridayLessons.visibility == View.VISIBLE && checked)
                || (fridayLessons.visibility == View.GONE && !checked))
            return

        val set = TransitionSet()
                .addTransition(Scale(0.7f))
                .addTransition(Fade())
                .setDuration(200L)
                .setInterpolator(LinearOutSlowInInterpolator())

        TransitionManager.beginDelayedTransition(scrollContainer, set)
        fridayLessons.visibility =
                if (checked)
                    View.VISIBLE
                else
                    View.GONE

        val myImageView = arrow_Friday

        val animSet = AnimationSet(true)
        animSet.interpolator = DecelerateInterpolator()
        animSet.fillAfter = true
        animSet.isFillEnabled = true

        val animRotate =
                if (checked)
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

        myImageView.startAnimation(animSet)
    }

    private fun changeThursdayVisible(checked: Boolean) {
        if ((thursdayLessons.visibility == View.VISIBLE && checked)
                || (thursdayLessons.visibility == View.GONE && !checked))
            return

        val set = TransitionSet()
                .addTransition(Scale(0.7f))
                .addTransition(Fade())
                .setDuration(200L)
                .setInterpolator(LinearOutSlowInInterpolator())

        TransitionManager.beginDelayedTransition(scrollContainer, set)
        thursdayLessons.visibility =
                if (checked)
                    View.VISIBLE
                else
                    View.GONE

        val myImageView = arrow_Thursday

        val animSet = AnimationSet(true)
        animSet.interpolator = DecelerateInterpolator()
        animSet.fillAfter = true
        animSet.isFillEnabled = true

        val animRotate =
                if (checked)
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

        myImageView.startAnimation(animSet)
    }

    private fun changeWednesdayVisible(checked: Boolean) {
        if ((wednesdayLessons.visibility == View.VISIBLE && checked)
                || (wednesdayLessons.visibility == View.GONE && !checked))
            return

        val set = TransitionSet()
                .addTransition(Scale(0.7f))
                .addTransition(Fade())
                .setDuration(200L)
                .setInterpolator(LinearOutSlowInInterpolator())

        TransitionManager.beginDelayedTransition(scrollContainer, set)
        wednesdayLessons.visibility =
                if (checked)
                    View.VISIBLE
                else
                    View.GONE

        val myImageView = arrow_Wednesday

        val animSet = AnimationSet(true)
        animSet.interpolator = DecelerateInterpolator()
        animSet.fillAfter = true
        animSet.isFillEnabled = true

        val animRotate =
                if (checked)
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

        myImageView.startAnimation(animSet)
    }


    private fun changeMondayVisible(checked: Boolean) {
        if ((mondayLessons.visibility == View.VISIBLE && checked)
                || (mondayLessons.visibility == View.GONE && !checked))
            return

        val set = TransitionSet()
                .addTransition(Scale(0.7f))
                .addTransition(Fade())
                .setDuration(200L)
                .setInterpolator(LinearOutSlowInInterpolator())

        TransitionManager.beginDelayedTransition(scrollContainer, set)
        mondayLessons.visibility =
                if (checked)
                    View.VISIBLE
                else
                    View.GONE

        val myImageView = arrow_Monday

        val animSet = AnimationSet(true)
        animSet.interpolator = DecelerateInterpolator()
        animSet.fillAfter = true
        animSet.isFillEnabled = true

        val animRotate =
                if (checked)
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

        myImageView.startAnimation(animSet)
    }

    private fun changeTuesdayVisible(checked: Boolean) {
        if ((tuesdayLessons.visibility == View.VISIBLE && checked)
                || (tuesdayLessons.visibility == View.GONE && !checked))
            return

        val set = TransitionSet()
                .addTransition(Scale(0.7f))
                .addTransition(Fade())
                .setDuration(200L)
                .setInterpolator(LinearOutSlowInInterpolator())

        TransitionManager.beginDelayedTransition(scrollContainer, set)
        tuesdayLessons.visibility =
                if (checked)
                    View.VISIBLE
                else
                    View.GONE

        val myImageView = arrow_Tuesday

        val animSet = AnimationSet(true)
        animSet.interpolator = DecelerateInterpolator()
        animSet.fillAfter = true
        animSet.isFillEnabled = true

        val animRotate =
                if (checked)
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

        myImageView.startAnimation(animSet)
    }

    enum class Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
    }
}
