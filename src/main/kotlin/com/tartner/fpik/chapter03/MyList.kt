package com.tartner.fpik.chapter03

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
    tailrec fun loop(index: Int): MyList<A> {
        if (index == 0) return Nil
        if (index == 1) return Cons(values.first(), Nil)

        return loop(index - 1)
    }

    return loop(values.size-1)
}
