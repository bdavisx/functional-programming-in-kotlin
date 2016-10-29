package com.tartner.fpik.chapter02

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert.assertEquals

fun <A,B,C> uncurry(function: (A) -> ((B) -> C)): (A,B) -> C {
    return fun(a: A, b: B): C {
        return function(a)(b)
    }
}

class UncurryTest: Spek({
    describe("uncurry function") {
        fun original(a: Int, b: Double): String {
            val result = a * b
            return "$result"
        }

        it("should curry the passed in function") {
            val curried = curry(::original)
            val uncurried = uncurry(curried)

            assertEquals("12.5", uncurried(5, 2.5))
        }
    }
})
