+++
title = "Currying and Uncurrying (Ex 2.3 and 2.4)"
draft = true
date = "2016-10-29T08:12:05-05:00"

+++

## Exercise 2.3 - Currying

The FPiS book says that there is only one implementation that compiles for Scala. I don't know if
that's true for Kotlin, but below is what I came up with. On line 1, we specify that the `function`
parameter is a function that takes an A and B, and returns a C: <br/>`function: (A,B) -> C`<br/> We
then say that we're returning a function that takes an A, and returns a function that takes a B and
returns a C:<br/> `(A) -> ((B) -> C)`<br/> We are basically declaring two functions in the return
declaration portion of the signature. 

Line 2 is where we declare and return the function that the user wants - a function that takes an A
and returns another function. We don't name the local functions that we are returning. On line 3 we
get to the function that does the actual 'work' of the curry - on line 4 the original function that
the user passed in is called, along with the parameters a and b that have been supplied in by two
separate calls.

{{< highlight kotlin "linenos=inline" >}}
fun <A,B,C> curry(function: (A,B) -> C): (A) -> ((B) -> C) {
    return fun (a: A): (B) -> C {
        return fun (b: B): C {
            return function(a, b)
        }
    }
}

// test
    describe("curry function") {
        fun original(a: Int, b: Double): String {
            val result = a * b
            return "$result"
        }

        it("should curry the passed in function") {
            val curried = curry(::original)
            val partiallyApplied = curried(5)

            assertEquals("12.5", partiallyApplied(2.5))
        }
    }
{{< / highlight >}}
