package com.android.petro.schooltable

/**
 * Created by petro on 02.10.2017.
 * Class provides day's titles
 */
enum class DayType(val dayName: String) {
    MONDAY("Понедельник"),
    TUESDAY("Вторник"),
    WEDNESDAY("Среда"),
    THURSDAY("Четверг"),
    FRIDAY("Пятница"),
    SATURDAY("Суббота");
    companion object {
        fun getDayByName(name: String): DayType = when (name) {
            "Monday" -> MONDAY
            "Tuesday" -> TUESDAY
            "Wednesday" -> WEDNESDAY
            "Thursday" -> THURSDAY
            "Friday" -> FRIDAY
            else -> SATURDAY
        }
    }
}