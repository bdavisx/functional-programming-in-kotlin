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

fun <A,B,C> uncurrySeparate(function: (A) -> ((B) -> C)): (A,B) -> C {
    return fun(a: A, b: B): C {
        val partiallyApplied = function(a)
        return partiallyApplied(b)
    }
}

class UncurryTest: Spek({
    describe("uncurry function") {
        it("should uncurry the passed in function") {
            val curried: (a: Int) -> (b: Double) -> String = curry(::functionToBeCurried)
            val uncurried: (a: Int, b: Double) -> String = uncurry(curried)

            assertEquals("12.5", uncurried(5, 2.5))
        }
    }
})
