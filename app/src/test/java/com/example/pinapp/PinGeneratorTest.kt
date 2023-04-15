package com.example.pinapp

import org.junit.Assert.assertEquals
import org.junit.Test

internal class PinGeneratorTest {

    @Test
    fun `test generate() pin size is 6`() {
        val sut = PinGenerator()
        val generated = sut.generate()
        println("Test generate() pin size is 6. Generated PIN $generated")
        assertEquals(6, generated.length)
    }

    @Test
    fun `test generate() pin contains only numbers`() {
        val sut = PinGenerator()
        val generated = sut.generate()
        println("Test generate() pin contains only numbers. Generated PIN $generated")
        assertEquals(true, generated.toIntOrNull() is Int)
    }
}