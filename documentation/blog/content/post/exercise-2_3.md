+++
title = "Exercise 2.3 - Currying and Exercise 2.4 - Uncurrying"
draft = true
date = "2016-10-29T08:12:05-05:00"

+++

## Exercise 2.3 - Currying

The FPiS book says that there is only one implementation that compiles for Scala. I don't know if
that's true for Kotlin, but below is what I came up with. On line 1, we specify that the 
`function` parameter is a function that takes an A and B, and returns a C (`function: (A,B) -> C)`). 
We then say that we're returning a function that takes an A, and returns a function that takes a 
B and returns a C (`(A) -> (B) -> C`). We are basically declaring two functions in the return 
clause. You can put parenthesis around the signature to make the multiple function declaration 
clearer also: `fun <A,B,C> curry(function: (A,B) -> C): (A) -> ((B) -> C) {`

{{< highlight kotlin "linenos=inline" >}}
fun <A,B,C> curry(function: (A,B) -> C): (A) -> (B) -> C {
    return fun (a: A): (B) -> C {
        return fun (b: B): C {
            return function(a, b)
        }
    }
}
{{< / highlight >}}
