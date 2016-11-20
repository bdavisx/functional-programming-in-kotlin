package ws.billdavis.fpik.chapter03

import io.kotlintest.KTestJUnitRunner
import io.kotlintest.specs.FeatureSpec
import org.junit.runner.RunWith

sealed class MyList<out A>
object Nil: MyList<Nothing>()
data class Cons<out A>(val head: A, val tail: MyList<A>): MyList<A>()

fun sum(ints: MyList<Int>): Int = when(ints) {
    Nil -> 0
    is Cons -> ints.head + sum(ints.tail)
}

fun product(ds: MyList<Double>): Double = when(ds) {
    Nil -> 1.0
    is Cons -> when(ds.head) {
        0.0 -> 0.0
        else -> ds.head * product(ds.tail)
    }
}

fun <A> myListOf(vararg values: A): MyList<A> {
    if(values.size == 0) return Nil;

    tailrec fun loop(list: MyList<A>, lastProcessedIndex: Int): MyList<A> {
        val index = lastProcessedIndex-1
        if (index < 0) return list

        return loop(Cons(values[index], list), index)
    }

    return loop(Nil, values.size)
}

@RunWith(KTestJUnitRunner::class)
class MyListTests: FeatureSpec() {
    init {
        feature("sum MyList<A>") {
            scenario("Nil should return 0") {
                sum(Nil) shouldBe 0
            }
            scenario("Correctly sum values") {
                sum(myListOf(5)) shouldBe 5
                sum(myListOf(1,5,7)) shouldBe 13
            }
        }
        feature("product MyList<A>") {
            scenario("Nil should return 1.0") {
                product(Nil) shouldBe (1.0 plusOrMinus 0.0)
            }
            scenario("Correctly multiply values") {
                product(myListOf(5.0)) shouldBe (5.0 plusOrMinus 0.0)
                product(myListOf(2.0, 5.0, 7.0)) shouldBe (70.0 plusOrMinus 0.0)
            }
        }
        feature("myListOf<A>") {
            scenario("create an empty list if not passed anything") {
                val test = myListOf<Int>()
                (test == Nil) shouldBe true
            }
            scenario("handle a single entry list and return the correct value from it") {
                val test: Cons<Int> = myListOf<Int>(1) as Cons<Int>
                test.head shouldBe 1
            }
            scenario("handle a multiple entry list and return the correct first value from it") {
                val test: Cons<Int> = myListOf<Int>(2, 1) as Cons<Int>
                test.head shouldBe 2
            }
            scenario("handle a multiple entry list and return the correct 2nd value from it") {
                val test: Cons<Int> = myListOf<Int>(2, 1) as Cons<Int>
                val next: Cons<Int> = test.tail as Cons<Int>
                next.head shouldBe 1
            }
        }
    }
}
