+++
title = "Currying and Uncurrying (Ex 2.3 and 2.4)"
draft = true
date = "2016-10-29T08:12:05-05:00"

+++

## Exercise 2.3 - Currying

The FPiS book says that there is only one implementation that compiles for Scala. I don't know if
that's true for Kotlin, but below is what I came up with. On line 6, we specify that the `function`
parameter is a function that takes an A and B, and returns a C: <br/>`function: (A,B) -> C`<br/> We
then say that we're returning a function that takes an A, and returns a function that takes a B and
returns a C:<br/> `(A) -> ((B) -> C)`<br/> We are basically declaring two functions in the return
declaration portion of the signature. 

Line 7 is where we declare and return the function that the user wants - a function that takes an A
and returns another function. We don't name the local functions that we are returning. On line 8 we
get to the function that does the actual 'work' of the curry - on line 9 the original function that
the user passed in is called, along with the parameters a and b that have been supplied in by two
separate calls..

// TODO: make sure the terms in the test are correct: curried and partiallyApplied.

<script src="https://gist.github.com/bdavisx/70e44e80886a3075db80965c6fa7a076.js"></script>
