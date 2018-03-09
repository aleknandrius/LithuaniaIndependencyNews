package com.telesoftas.lithuaniaindependencynews.utils.formater

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateFormaterImpl: DateFormater {
    override fun getFormatedString(date: String): String {
        return try {
            var parsedDate = SimpleDateFormat(DATE_FORMAT_PARSED).parse(date)
            val dateFormatToShow = SimpleDateFormat(DATE_FORMAT_TO_SHOW, Locale.ENGLISH)
            dateFormatToShow.format(parsedDate)
        } catch (e: ParseException) {
            e.printStackTrace()
            date
        }
    }

    companion object {
        const val DATE_FORMAT_PARSED = "yyyy-MM-dd'T'hh:mm:ss"
        const val DATE_FORMAT_TO_SHOW = "yyyy-MMMM-dd hh:mm"
    }

}