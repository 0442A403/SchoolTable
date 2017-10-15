package com.android.petro.schooltable

import android.content.Context
import android.graphics.Typeface
import android.support.constraint.ConstraintLayout
import android.support.percent.PercentRelativeLayout
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.ScrollView
import org.jetbrains.anko.*

/**
 * Created by petro on 01.11.2017.
 * Anko ui class
 */

class UserInterface(private val mContext: Context) : AnkoComponent<MainActivity> {
    private val dayHashMap = HashMap<String, LinearLayout>()
    private var layout: ScrollView? = null
    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
        layout = scrollView {
            isVerticalScrollBarEnabled = false
            setPadding(16, 0, 16, 0)
            linearLayout {
                orientation = LinearLayout.VERTICAL

                frameLayout().lparams {
                    height = dip(16)
                }
                val monday = getDayLayout(R.id.Monday)
                getLineView()
                val tuesday = getDayLayout(R.id.Tuesday)
                getLineView()
                val wednesday = getDayLayout(R.id.Wednesday)
                getLineView()
                val thursday = getDayLayout(R.id.Thursday)
                getLineView()
                val friday = getDayLayout(R.id.Friday)
                getLineView()
                val saturday = getDayLayout(R.id.Saturday)
                frameLayout().lparams {
                    height = dip(16)
                }
                dayHashMap["Monday"] = monday
                dayHashMap["Tuesday"] = tuesday
                dayHashMap["Wednesday"] = wednesday
                dayHashMap["Thursday"] = thursday
                dayHashMap["Friday"] = friday
                dayHashMap["Saturday"] = saturday
            }
        }
        return layout!!
    }

    private fun _LinearLayout.getDayLayout(dayId: Int): LinearLayout = linearLayout {
        orientation = LinearLayout.VERTICAL
        id = dayId

        relativeLayout {
            id = R.id.dayTitle_Container
            textView {
                id = R.id.dayTitle
                textColor = ContextCompat.getColor(mContext, R.color.lessonColor)
                textSize = sp(30).toFloat()
                typeface = Typeface.DEFAULT_BOLD
            }

            imageView {
                id = R.id.arrow
                setImageResource(R.drawable.arrow_down)
            }.lparams {
                rightMargin = dip(4)
                alignParentRight()
                centerVertically()
            }
        }

        linearLayout {
            orientation = LinearLayout.VERTICAL
            visibility = View.GONE
            id = R.id.lessons
            include<ConstraintLayout>(R.layout.lesson) { id = R.id.lesson0 }
            include<ConstraintLayout>(R.layout.lesson) { id = R.id.lesson1 }
            include<ConstraintLayout>(R.layout.lesson) { id = R.id.lesson2 }
            include<ConstraintLayout>(R.layout.lesson) { id = R.id.lesson3 }
            include<ConstraintLayout>(R.layout.lesson) { id = R.id.lesson4 }
            include<ConstraintLayout>(R.layout.lesson) { id = R.id.lesson5 }
            include<ConstraintLayout>(R.layout.lesson) { id = R.id.lesson6 }
            include<ConstraintLayout>(R.layout.lesson) { id = R.id.lesson7 }
            include<ConstraintLayout>(R.layout.lesson) { id = R.id.lesson8 }
        }.lparams {
            width = matchParent
            leftPadding = dip(8)
            rightPadding = dip(8)
        }

        linearLayout {
            orientation = LinearLayout.VERTICAL
            visibility = View.GONE
            id = R.id.lessons_EditText
            include<PercentRelativeLayout>(R.layout.lesson__edit_text) { id = R.id.lesson0__edit_text }
            include<PercentRelativeLayout>(R.layout.lesson__edit_text) { id = R.id.lesson1__edit_text }
            include<PercentRelativeLayout>(R.layout.lesson__edit_text) { id = R.id.lesson2__edit_text }
            include<PercentRelativeLayout>(R.layout.lesson__edit_text) { id = R.id.lesson3__edit_text }
            include<PercentRelativeLayout>(R.layout.lesson__edit_text) { id = R.id.lesson4__edit_text }
            include<PercentRelativeLayout>(R.layout.lesson__edit_text) { id = R.id.lesson5__edit_text }
            include<PercentRelativeLayout>(R.layout.lesson__edit_text) { id = R.id.lesson6__edit_text }
            include<PercentRelativeLayout>(R.layout.lesson__edit_text) { id = R.id.lesson7__edit_text }
            include<PercentRelativeLayout>(R.layout.lesson__edit_text) { id = R.id.lesson8__edit_text }
        }.lparams {
            leftPadding = dip(8)
            rightPadding = dip(8)
        }
    }

    private fun _LinearLayout.getLineView()  = view {
        setBackgroundColor(ContextCompat.getColor(mContext, R.color.underlineColor))
    }.lparams {
        width = matchParent
        height = dip(1)
        topMargin = dip(8)
        leftMargin = dip(16)
        rightMargin = dip(16)
        bottomMargin = dip(8)
    }

    private fun _LinearLayout.getLessonLayout(lessonId: Int): RelativeLayout = relativeLayout {
        id = lessonId
        relativeLayout {
            id = R.id.lessonWithoutTimeContainer
            orientation = LinearLayout.HORIZONTAL
            textView {
                id = R.id.lessonsClassroom
                textColor = ContextCompat.getColor(mContext, R.color.classroomColor)
                textSize = sp(23).toFloat()
                ellipsize = TextUtils.TruncateAt.END
                maxLines = 1
                typeface = Typeface.DEFAULT_BOLD
            }.lparams {
                alignParentRight()
                centerVertically()
            }
            linearLayout {
                id = R.id.lessonsTitleContainer
                orientation = LinearLayout.HORIZONTAL
                textView {
                    id = R.id.lessonIndex
                    textColor = ContextCompat.getColor(mContext, R.color.lessonColor)
                    textSize = sp(21).toFloat()
                    ellipsize = TextUtils.TruncateAt.END
                    maxLines = 1
                    typeface = Typeface.DEFAULT_BOLD
                }
                textView {
                    id = R.id.lesson
                    textColor = ContextCompat.getColor(mContext, R.color.lessonColor)
                    textSize = sp(21).toFloat()
                    ellipsize = TextUtils.TruncateAt.END
                    maxLines = 1
                    typeface = Typeface.DEFAULT_BOLD
                }
            }.lparams {
                alignParentLeft()
                leftOf(R.id.lessonsClassroom)
            }
        }
        linearLayout {
            orientation = LinearLayout.HORIZONTAL

            textView {
                id = R.id.lessonStartTime
                textColor = ContextCompat.getColor(mContext, R.color.lessonTimeColor)
                textSize = sp(15).toFloat()
                maxLines = 1
            }

            textView("-") {
                textSize = sp(15).toFloat()
                textColor = ContextCompat.getColor(mContext, R.color.lessonTimeColor)
                ellipsize = TextUtils.TruncateAt.END
                maxLines = 1
            }

            textView {
                id = R.id.lessonEndTime
                textColor = ContextCompat.getColor(mContext, R.color.lessonTimeColor)
                textSize = sp(15).toFloat()
                ellipsize = TextUtils.TruncateAt.END
                maxLines = 1
            }

        }.lparams {
            below(R.id.lessonWithoutTimeContainer)
        }
    }



    fun getLayout(): ScrollView = layout!!

    fun getDictionary(): HashMap<String, LinearLayout> = dayHashMap
}