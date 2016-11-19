package ws.billdavis.fpik.chapter02

import io.kotlintest.KTestJUnitRunner
import io.kotlintest.specs.FeatureSpec
import org.junit.runner.RunWith

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

@RunWith(KTestJUnitRunner::class)
class FibonacciTest: FeatureSpec() {
    init {
        val expectedValues = arrayOf(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610,
            987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811)

        // these are the same tests as earlier using a higher order function
        fun runFibonacciTests(function: (Int)->Int) {
            scenario("should return the correct first value") {
                function(0) shouldBe 0
            }

            scenario("should return the correct values in expectedValues") {
                for (i in 0..(expectedValues.size - 1)) {
                    val value = function(i)
                    value shouldBe expectedValues[i]
                }
            }
        }

        feature("fibonacci functions tested using higher order functions") {
            runFibonacciTests(::fibonacci)
            runFibonacciTests(::fibonacci2)
        }
    }
}
















