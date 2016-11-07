package ws.billdavis.fpik.chapter02

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertFalse
import kotlin.test.assertTrue

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

class IsSortedTest: Spek({
    describe("isSorted function") {
        val sortedArray = arrayOf(2, 4, 6, 8, 10)
        val emptyArray = arrayOf<Int>()
        val unsortedArray = arrayOf(5,4,3)

        it("Should return true for a sorted array") {
            assertTrue(isSorted(sortedArray, { lhs, rhs -> lhs < rhs }))
        }

        it("Should return true for an empty array") {
            assertTrue(isSorted(emptyArray, { lhs, rhs -> lhs < rhs }))
        }

        it("Should return false for an unsorted array") {
            assertFalse(isSorted(unsortedArray, { lhs, rhs -> lhs < rhs }))
        }
    }
})

