package com.michaelvolk.mathegames.math

class Utils {

    companion object {
        fun getRandomNumbers(lower: Int=0, upper: Int=10, count: Int = 5 ): ArrayList<Int> {
            var list = ArrayList<Int>()
            while (list.size < count) {
                var num = (lower..upper ).random()
                if (!list.contains(num)) list.add(num)
            }
            return list
        }
    }
}