package com.example.pinapp

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class PinValidatorTest {

    @Test
    fun `test isValid() too much repeated numbers false 1`() {
        val sut = PinValidator()
        assertEquals(false, sut.isValid("111001"))
    }

    @Test
    fun `test isValid() too much repeated numbers false 2`() {
        val sut = PinValidator()
        assertEquals(false, sut.isValid("111011"))
    }

    @Test
    fun `test isValid() too much repeated numbers false 3`() {
        val sut = PinValidator()
        assertFalse(sut.isValid("220022"))
    }

    @Test
    fun `test isValid() too much repeated numbers false 4`() {
        val sut = PinValidator()
        assertEquals(false, sut.isValid("777777"))
    }

    @Test
    fun `test isValid() pin length to short false 1`() {
        val sut = PinValidator()
        assertFalse(sut.isValid("10112"))
    }

    @Test
    fun `test isValid() pin length to short false 2`() {
        val sut = PinValidator()
        assertFalse(sut.isValid("2"))
    }

    @Test
    fun `test isValid() pin length to long false 1`() {
        val sut = PinValidator()
        assertFalse(sut.isValid("1011433"))
    }

    @Test
    fun `test isValid() pin length to long false 2`() {
        val sut = PinValidator()
        assertFalse(sut.isValid("1234567890"))
    }

    @Test
    fun `test isValid() pin is empty false`() {
        val sut = PinValidator()
        assertFalse(sut.isValid(""))
    }

    @Test
    fun `test isValid() pin has white chars false 1`() {
        val sut = PinValidator()
        assertFalse(sut.isValid("333 22"))
    }

    @Test
    fun `test isValid() pin has white chars false 2`() {
        val sut = PinValidator()
        assertFalse(sut.isValid("3 3 2 "))
    }

    @Test
    fun `test isValid() pin has white chars false 3`() {
        val sut = PinValidator()
        assertFalse(sut.isValid("      "))
    }

    @Test
    fun `test isValid() pin has other chars than ints false 1`() {
        val sut = PinValidator()
        assertFalse(sut.isValid("3s382s"))
    }

    @Test
    fun `test isValid() pin has other chars than ints false 2`() {
        val sut = PinValidator()
        assertFalse(sut.isValid("$#@344"))
    }

    @Test
    fun `test isValid() pin has other chars than ints false 3`() {
        val sut = PinValidator()
        assertFalse(sut.isValid("2--344"))
    }

    @Test
    fun `test isValid() pin is correct true 1`() {
        val sut = PinValidator()
        assertTrue(sut.isValid("333000"))
    }

    @Test
    fun `test isValid() pin is correct true 2`() {
        val sut = PinValidator()
        assertTrue(sut.isValid("112233"))
    }

    @Test
    fun `test isValid() pin is correct true 3`() {
        val sut = PinValidator()
        assertTrue(sut.isValid("987654"))
    }
}