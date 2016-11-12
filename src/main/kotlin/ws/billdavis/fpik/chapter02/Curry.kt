package ws.billdavis.fpik.chapter02

import io.kotlintest.KTestJUnitRunner
import io.kotlintest.specs.FeatureSpec
import org.junit.runner.RunWith

fun <A,B,C> curry(function: (A,B) -> C): (A) -> ((B) -> C) {
    return fun (a: A): (B) -> C {
        return fun (b: B): C {
            return function(a, b)
        }
    }
}

@RunWith(KTestJUnitRunner::class)
class CurryTest: FeatureSpec() {
    init {
        feature("curry function") {
            scenario("should curry the passed in function") {
                val curried: (a: Int)->(b: Double)->String = curry(::functionToBeCurried)
                val partiallyAppliedWith5: (b: Double)->String = curried(5)
                val fullyExecuted: String = partiallyAppliedWith5(2.5)

                fullyExecuted shouldBe "12.5"
            }
        }
    }
}

fun functionToBeCurried(a: Int, b: Double): String {
    val result = a * b
    return "$result"
}
