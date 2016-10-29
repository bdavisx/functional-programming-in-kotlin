package com.tartner.fpik

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Ch02Test : Spek({
    val expectedValues = arrayOf(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610,
        987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811)

    fun runFibonacciTests(function: (Int) -> Int) {
        it("should return the correct first value") {
            assertEquals(0, function(0))
        }

        it("should return the correct values in expectedValues") {
            for( i in 0..(expectedValues.size-1) ) {
                val value = function(i)
                assertEquals(expectedValues[i], value)
            }
        }
    }

    describe("fibonacci functions") {
        runFibonacciTests(::fibonacci)
        runFibonacciTests(::fibonacci2)
    }


    describe("isSorted function") {
        val sortedArray = arrayOf(2, 4, 6, 8, 10)
        val emptyArray = arrayOf<Int>()
        val unsortedArray = arrayOf(5,4,3)

        it("Should return true for a sorted array") {
            assertTrue( isSorted(sortedArray, {lhs, rhs -> lhs < rhs } ) )
        }

        it("Should return true for an empty array") {
            assertTrue( isSorted(emptyArray, {lhs, rhs -> lhs < rhs } ) )
        }

        it("Should return false for an unsorted array") {
            assertFalse( isSorted(unsortedArray, {lhs, rhs -> lhs < rhs } ) )
        }
    }

    describe("curry function") {
        fun original(a: Int, b: Double): String {
            val result = a * b
            return "$result"
        }

        it("should curry the passed in function") {
            val curried = curry(::original)
            val partiallyApplied = curried(5)

            assertEquals("12.5", partiallyApplied(2.5))
        }
    }
})

