package es.jrhtfld.domain

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String?.getDateFormatted(): CharSequence? {
    val date = this?.getDateWithServerTimeStamp()
    val dateFormat = SimpleDateFormat("d MMMM yyyy '|' HH:mm",
        Locale("es"))
    dateFormat.timeZone = TimeZone.getTimeZone("GMT")
    return dateFormat.format(date)
}

@SuppressLint("SimpleDateFormat")
fun String.parseDate(inputFormat: String, outputFormat: String) : String {
    val mInputFormat: DateFormat = SimpleDateFormat(inputFormat, Locale.getDefault())
    val mOutputFormat: DateFormat = SimpleDateFormat(outputFormat, Locale.getDefault())
    return mOutputFormat.format(mInputFormat.parse(this@parseDate))
}

///OBS Date Formats:
@SuppressLint("SimpleDateFormat")
fun String.date24To12(): String {
    val inputFormat: DateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    val outputFormat: DateFormat = SimpleDateFormat("KK:mm a", Locale.getDefault())
    return outputFormat.format(inputFormat.parse(this@date24To12))
}

@SuppressLint("SimpleDateFormat")
fun String.parseCompleteDateToDay() :String {
    val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    val outputFormat: DateFormat = SimpleDateFormat("dd", Locale.getDefault())
    return outputFormat.format(inputFormat.parse(this@parseCompleteDateToDay))
}

@SuppressLint("SimpleDateFormat")
fun String.parseCompleteDateToMonthAndDayAndHour() :String {
    val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    val outputFormat: DateFormat = SimpleDateFormat("dd/MM HH:mm", Locale.getDefault())
    return outputFormat.format(inputFormat.parse(this@parseCompleteDateToMonthAndDayAndHour))
}

@SuppressLint("SimpleDateFormat")
fun String.dateToDay() :String {
    val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    val outputFormat: DateFormat = SimpleDateFormat("dd", Locale.getDefault())
    Log.d(TAG, "dateToDay: ${outputFormat.format(inputFormat.parse(this@dateToDay))}")
    return outputFormat.format(inputFormat.parse(this@dateToDay))
}

@SuppressLint("SimpleDateFormat")
fun String.dateToMonth() :String {
    val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    val outputFormat: DateFormat = SimpleDateFormat("MM", Locale.getDefault())
    Log.d(TAG, "dateToMonth: ${outputFormat.format(inputFormat.parse(this@dateToMonth))}")
    return outputFormat.format(inputFormat.parse(this@dateToMonth))
}

@SuppressLint("SimpleDateFormat")
fun String.parseCompleteDateToHour() :String {
    val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    val outputFormat: DateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    return outputFormat.format(inputFormat.parse(this@parseCompleteDateToHour))
}
///

fun String.getDateWithServerTimeStamp(): Date? {
    val dateFormat = SimpleDateFormat(
        "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
        Locale.getDefault()
    )
    dateFormat.timeZone = TimeZone.getTimeZone("GMT")
    return try {
        dateFormat.parse(this)
    } catch (e: ParseException) {
        null
    }
}

fun String.getTimestampFromCompleteDate2(): String {
    val formatter: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    val formattingDate = formatter.parse(this@getTimestampFromCompleteDate2) as Date
    return formattingDate.time.toString()
}

@SuppressLint("SimpleDateFormat")
fun Calendar.getTimestamp(): String {
    val df = SimpleDateFormat("yyyy-MM-dd-HH:mm:ss.SSS", Locale.getDefault())
    val date = this.time
    return df.format(date)
}

fun getActualCalendar(): Calendar {
    return Calendar.getInstance()
}