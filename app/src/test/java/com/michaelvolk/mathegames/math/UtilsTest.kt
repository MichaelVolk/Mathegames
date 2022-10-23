package com.michaelvolk.mathegames.math

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test


internal class UtilsTest {

    @Test fun getRandomNumbers_fails_counttoohigh() {
        Assert.assertThrows(IllegalArgumentException::class.java) {
            Utils.getRandomNumbers(0,5,10)
        }

    }

    @Test fun getRandomNumbers_fails_illegalboundaries() {
        Assert.assertThrows(IllegalArgumentException::class.java) {
            Utils.getRandomNumbers(10,5,1)
        }
        Assert.assertThrows(IllegalArgumentException::class.java) {
            Utils.getRandomNumbers(10,10,1)
        }
    }

    @Test fun getRandonNumbers_numbersAreDifferent() {
        val list = Utils.getRandomNumbers()
        for(i in 0 until list.size) {
            for(j in 0 until list.size) {
                if(i != j) {
                    assertNotEquals(list[i], list[j])
                }
            }
        }
    }
}