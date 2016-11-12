package ws.billdavis.fpik.chapter03

import io.kotlintest.KTestJUnitRunner
import io.kotlintest.specs.FeatureSpec
import io.kotlintest.specs.ShouldSpec
import org.junit.Assert
import org.junit.runner.RunWith
import ws.billdavis.fpik.chapter02.uncurry

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

    tailrec fun loop(myList: MyList<A>, index: Int): MyList<A> {
        if (index == 0) return myList
        if (index == 1) return Cons(values.first(), Nil)

        return loop(Cons(values[index], myList), index-1)
    }

    return loop(Cons(values.first(), Nil), values.size-1)
}

@RunWith(KTestJUnitRunner::class)
class MyListTests: FeatureSpec() {
    init {
        feature("myListOf") {
            scenario("create an empty list if not passed anything") {
                val test = myListOf<Int>()
                (test == Nil) shouldBe true
            }
        }
    }
}
