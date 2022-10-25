package com.michaelvolk.mathegames.math


class Utils {

    companion object {
        fun getRandomNumbers(lower: Int=0, upper: Int=10, count: Int = 5 ): ArrayList<Int> {
            /**
             * Returns a list of different random integers in a given interval
             *
             * @param lower the lower bound of the interval
             * @param upper the upper bound of the interval
             * @param count amount of different numbers returns
             * @throws IllegalArgumentException if count exceeds the number of numbers in the given
             * interval
             * @throws IllegalArgumentException if lower is higher than upper
             *
             *
             *
             */
            if(upper <= lower) {
               throw java.lang.IllegalArgumentException("lower must be lower than upper")
            }

            if((upper - lower) + 1 < count) {
                throw java.lang.IllegalArgumentException("count can't be larger than the amount " +
                        "of numbers between upper and lower!")
            }
            val list = ArrayList<Int>()
            while (list.size < count) {
                val num = (lower..upper ).random()
                if (!list.contains(num)) list.add(num)
            }
            return list
        }

        fun num2roman(input: Int = 1): String {
            if(input < 1) throw java.lang.IllegalArgumentException("Number must be greater than 0")
            var output = ""
            var num = input
            while(num > 1000) {
                num -= 1000
                output = "M$output"
            }

            return output
        }
    }
}