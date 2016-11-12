package ws.billdavis.fpik.chapter02

import io.kotlintest.KTestJUnitRunner
import io.kotlintest.specs.ShouldSpec
import org.junit.runner.RunWith

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

@RunWith(KTestJUnitRunner::class)
class UncurryTests: ShouldSpec() {
    init {
        "uncurry function" {
            should("should uncurry the passed in function") {
                val curried: (a: Int)->(b: Double)->String = curry(::functionToBeCurried)
                val uncurried: (a: Int, b: Double) -> String = uncurry(curried)

                uncurried(5, 2.5) shouldBe "12.5"
            }
        }
    }
}
