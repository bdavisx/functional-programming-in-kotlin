package ws.billdavis.fpik.chapter03

import io.kotlintest.KTestJUnitRunner
import io.kotlintest.specs.FeatureSpec
import org.junit.runner.RunWith

fun <A,B> foldRight(list: MyList<A>, zero: B, f: (A,B) -> B): B = when(list) {
    is Nil -> zero
    is Cons -> f(list.head, foldRight(list.tail, zero, f))
}

fun productFR(ds: MyList<Double>): Double = foldRight(ds, 1.0, {a, b -> a*b})
fun <A> length(list: MyList<A>): Int = foldRight(list, 0, {a, b -> b+1})

fun <A> append(list: MyList<A>, a: A) = foldRight<A, MyList<A>>(list, Cons(a, Nil), {
    a: A, b: MyList<A> -> Cons(a,b)})

@RunWith(KTestJUnitRunner::class)
class FoldRightVersionsTests: FeatureSpec() {
    init {
        feature("append MyList<A>") {
            scenario("appending to Nil should return item") {
                append(Nil, 5) shouldBe myListOf(5)
            }
            scenario("appending to single element should return element and new element") {
                append(myListOf(1), 2) shouldBe myListOf(1, 2)
            }
            scenario("multi element list should append correctly") {
                append(myListOf(1,2,3,4),5) shouldBe myListOf(1,2,3,4,5)
            }
        }
        feature("length MyList<A>") {
            scenario("Nil should return 0") {
                length(Nil) shouldBe 0
            }
            scenario("single element should return 1") {
                length(myListOf(1)) shouldBe 1
            }
            scenario("multi element list should return correct length") {
                length(myListOf(1,2,3,4)) shouldBe 4
            }
        }
        feature("productFR MyList<A>") {
            scenario("Nil should return 1.0") {
                productFR(Nil) shouldBe (1.0 plusOrMinus 0.0)
            }
            scenario("Correctly multiply values") {
                productFR(myListOf(5.0)) shouldBe (5.0 plusOrMinus 0.0)
                productFR(myListOf(2.0, 5.0, 7.0)) shouldBe (70.0 plusOrMinus 0.0)
            }
            scenario("pass Nil and Cons to foldRight") {
                val x : MyList<Int> = foldRight<Int, MyList<Int>>(myListOf(1,2,3,4), Nil, {
                    a: Int, b: MyList<Int> -> Cons(a,b)})
                x shouldBe myListOf(1,2,3,4)
            }
        }
    }
}
