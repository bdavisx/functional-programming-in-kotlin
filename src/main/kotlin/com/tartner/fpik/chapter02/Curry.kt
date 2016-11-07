package com.tartner.fpik.chapter02

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert.assertEquals

fun <A,B,C> curry(function: (A,B) -> C): (A) -> ((B) -> C) {
    return fun (a: A): (B) -> C {
        return fun (b: B): C {
            return function(a, b)
        }
    }
}

class CurryTest: Spek({
    describe("curry function") {
        it("should curry the passed in function") {
            val curried: (a: Int) -> (b: Double) -> String = curry(::functionToBeCurried)
            val partiallyAppliedWith5: (b: Double) -> String = curried(5)
            val fullyExecuted: String = partiallyAppliedWith5(2.5)

            assertEquals("12.5", fullyExecuted)
        }
    }
})

fun functionToBeCurried(a: Int, b: Double): String {
    val result = a * b
    return "$result"
}
