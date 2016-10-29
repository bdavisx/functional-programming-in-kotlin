+++
title = "Fibonacci and Higher Order Functions (Ex 2.1 and 2.2)"
draft = false
date = "2016-10-28T12:29:04-05:00"

+++

I'm going to be writing about implementing exercises from ["Functional Programming in Scala" 
(FPiS))](https://www.manning.com/books/functional-programming-in-scala)
using the Kotlin language. 

# Exercise 2.1 - Fibonacci
The first exercise is to write a fibonacci function that is tail recursive. The result is similar to
the Scala version. 

The `fibonacci()` function starts out by declaring a local function `loop()` that does most of the
work. On line 8, we can just return the value of the `when()` statement directly, without needing to
create a variable. The `loop()` function just counts the sequence down, adding each value as it
counts down (line 10). When it gets to 0 (line 9), it has added all of the elements together and 
returns
 the
result. The function is marked tail recursive, which was one of the points of the exercise. If the
function you create is not tail recursive and you mark it with `tailrec`, you will get a warning
from the compiler. I created a test using [Spek] (https://jetbrains.github.io/spek/), then copied
the expected values from a website.
 
<script src="https://gist.github.com/bdavisx/8816e95f39376c1dfd6d8eec904be598.js"></script>

I considered an `if/else` statement in the `loop()` inner function, but I think the `when()` version
is more compact and [idiomatic](https://kotlinlang.org/docs/reference/idioms.html).
 
<script src="https://gist.github.com/bdavisx/0dcd00c9abf7da97ceecd3ac655beedc.js"></script>
 
# Exercise 2.2 - isSorted Higher Order Function

This exercise is "Implement isSorted, which checks whether an Array[A] is sorted according to a
given comparison function." We need to use a local function again to be able to be tail recursive.
The `loop()` function takes a `current` array index and if we've reached the end of the array (line
10), we return `true`, the entire array is sorted. Otherwise we check to see if the current element
is greater than the previous element. If not, we return false and short-circuit the check (line 12).
Otherwise we recursively call ourself with the next array index.

The `loop()` function has access to all of the parameters passed into the parent function. So it
does not need the array or ordered parameters to be passed to it.

Outside the local `loop()` function (line 19), we first do a check, if the array is empty, we 
just return true, otherwise we start the recursive `loop()` call. 

<script src="https://gist.github.com/bdavisx/4c363707c1cd041098398fd3efe2b5e0.js"></script>

## Using a Higher Order Function with Testing

After I wrote the `fibonacci2()` function (the `if/then` version), I basically copied the test and
called `fibonacci2()` instead, then refactored the test to use a higher-order function that would
run the tests for both functions. The `runFibonacciTests()` (line 5) function takes as a parameter,
a function that gets passed and int and returns an int. That signature is the same as both of the
fibonacci functions, so we can just pass those to the function to run the tests (lines 19-20).
  
<script src="https://gist.github.com/bdavisx/dc9582296694bb07cb849ccc73e6a7c6.js"></script>

