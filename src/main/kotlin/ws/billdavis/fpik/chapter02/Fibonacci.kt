package ws.billdavis.fpik.chapter02

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert

fun fibonacci(n: Int): Int {
    tailrec fun loop(n: Int, previous: Int, current: Int): Int {
        return when(n) {
            0 -> previous
            else -> loop(n-1, current, previous+current)
        }
    }

    return loop(n, 0, 1)
}

fun fibonacci2(n: Int): Int {
    tailrec fun loop(n: Int, previous: Int, current: Int): Int {
        return if( n == 0 ) {
            previous
        } else {
            loop(n-1, current, previous+current)
        }
    }

    return loop(n, 0, 1)
}

class FibonacciTest : Spek({
    val expectedValues = arrayOf(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610,
        987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811)

    fun runFibonacciTests(function: (Int) -> Int) {
        it("should return the correct first value") {
            Assert.assertEquals(0, function(0))
        }

        it("should return the correct values in expectedValues") {
            for( i in 0..(expectedValues.size-1) ) {
                val value = function(i)
                Assert.assertEquals(expectedValues[i], value)
            }
        }
    }

    describe("fibonacci functions") {
        runFibonacciTests(::fibonacci)
        runFibonacciTests(::fibonacci2)
    }
})
















