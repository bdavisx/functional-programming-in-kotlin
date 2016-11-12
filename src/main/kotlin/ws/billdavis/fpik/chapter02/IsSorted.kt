package ws.billdavis.fpik.chapter02

import io.kotlintest.KTestJUnitRunner
import io.kotlintest.specs.FeatureSpec
import org.junit.runner.RunWith

fun <A> isSorted(array: Array<A>, ordered: (A,A) -> Boolean): Boolean {
    tailrec fun loop(current: Int): Boolean {
        when(current) {
            array.size -> return true
            else -> {
                if(!ordered(array[current-1], array[current])) { return false }

                return loop(current+1)
            }
        }
    }

    if(array.size == 0) return true

    return loop(1)
}

@RunWith(KTestJUnitRunner::class)
class IsSortedTest: FeatureSpec(){
    init {
        feature("isSorted function") {
            val sortedArray = arrayOf(2, 4, 6, 8, 10)
            val emptyArray = arrayOf<Int>()
            val unsortedArray = arrayOf(5, 4, 3)

            scenario("Should return true for a sorted array") {
                isSorted(sortedArray, { lhs, rhs -> lhs < rhs }) shouldBe true
            }

            scenario("Should return true for an empty array") {
                isSorted(emptyArray, { lhs, rhs -> lhs < rhs }) shouldBe true
            }

            scenario("Should return false for an unsorted array") {
                isSorted(unsortedArray, { lhs, rhs -> lhs < rhs }) shouldBe false
            }
        }
    }
}

