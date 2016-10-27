package com.tartner.fpik

fun fibonacci(n: Int): Int {
    tailrec fun loop(n: Int, previous: Int, current: Int): Int {
        return when(n) {
            0 -> previous
            else -> { loop(n-1, current, previous+current) }
        }
    }

    return loop(n, 0, 1)
}

fun fibonacci2(n: Int): Int {
    tailrec fun loop(n: Int, previous: Int, current: Int): Int {
        return if( n == 0 ) {
            previous
        } else {
            loop(n-1, current, previous+current)
        }
    }

    return loop(n, 0, 1)
}

fun <A> isSorted(array: Array<A>, ordered: (A,A) -> Boolean): Boolean {
    tailrec fun loop(current: Int): Boolean {
        when(current) {
            array.size -> { return true }
            else -> {
                if(!ordered(array[current-1], array[current])) { return false }

                return loop(current+1)
            }
        }
    }

    if(array.size == 0) return true

    return loop(1)
}
