package com.example.pinapp

class PinGenerator {

    private val list = mutableListOf<Pair<Int, Int>>()

    fun generate(): String {
        var result = ""
        initList()

        // first 3 numbers
        for (i in 1..3) {
            val number = (0..9).random()
            result += number
        }

        // last 3 numbers
        for (i in 1..3) {
            val number = getRandom(list)
            result += number
        }
        println("Generated PIN $result")
        return result
    }

    private fun getRandom(list: MutableList<Pair<Int,Int>>): Int {
        val index = (0 until list.size).random()
        val number = list[index].first
        val selectedPair = list[index]
        list.removeAt(index)
        if (selectedPair.second < 3) {
            list.add(Pair(selectedPair.first, selectedPair.second + 1))
        }
        return number
    }

    private fun initList() {
        list.add(Pair(0,0))
        list.add(Pair(1,0))
        list.add(Pair(2,0))
        list.add(Pair(3,0))
        list.add(Pair(4,0))
        list.add(Pair(5,0))
        list.add(Pair(6,0))
        list.add(Pair(7,0))
        list.add(Pair(8,0))
        list.add(Pair(9,0))
    }
}