package com.syedabdullah.diceroller

import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun generates_number(){
        val dice=Dice(6)
        val rollResult=dice.roll()
        assertTrue("The value of rollResult was not between 1 and 6",rollResult in 1..6)
    }

    @Test
    fun check_rewards(){
        val dice=Dice(6)
        assertEquals(1,dice.getRewards((1..10).random()))
        assertEquals(5,dice.getRewards((11..20).random()))
        assertEquals(10,dice.getRewards((21..30).random()))
        assertEquals(15,dice.getRewards((31..40).random()))
        assertEquals(20,dice.getRewards((41..50).random()))
        assertEquals(50,dice.getRewards((51..100).random()))
    }

    @Test(timeout = 1000)
    fun check_timeout(){
        val dice=Dice(6)
        assertTrue(dice.timeout(100000))
    }

    @Test
    fun checkNullObject()
    {
        val dice=Dice(6)
        assertNotNull(dice.getPersonObj())
    }

    @Test
    fun addition(){
        val dice=Dice(6)
        assertEquals(4,dice.addition(2,2))
        assertEquals(7,dice.addition(3,4))
    }





}