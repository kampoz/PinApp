package com.example.pinapp

class PinValidator {

    fun isValid(pinCode: String): Boolean {

        if (pinCode.length != 6) {
            return false
        }

        if (pinCode.toIntOrNull() == null){
            return false
        }

        val data = mutableMapOf<Int, Int>()
        data[0] = 0
        data[1] = 0
        data[2] = 0
        data[3] = 0
        data[4] = 0
        data[5] = 0
        data[6] = 0
        data[7] = 0
        data[8] = 0
        data[9] = 0

        pinCode.forEach {
            val index: Int = it.digitToInt()
            val value: Int? = data[index]
            if (value != null) {
                data[index] = value + 1
            }
        }

        if (data.any {it.value > 3} ) {
            return false
        }
        return true
    }
}