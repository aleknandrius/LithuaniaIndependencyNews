package com.telesoftas.lithuaniaindependencynews.utils.formater

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class DateFormaterImplTest {
    private lateinit var formater: DateFormaterImpl
    private val INCORRECT_DATE = ""
    private val CORRECT_DATE = "2018-03-16T10:15:09Z"
    private val  CORRECT_RESULT = "2018-March-16 10:15"

    @Before
    fun setUp() {
        formater = DateFormaterImpl()
    }

    @Test
    fun getFormatedString_returnsSameString() {
        assertEquals(formater.getFormatedString(INCORRECT_DATE),INCORRECT_DATE)
    }

    @Test
    fun getFormatedString_returnsFormatedString() {
        assertEquals(formater.getFormatedString(CORRECT_DATE),CORRECT_RESULT)
    }

}