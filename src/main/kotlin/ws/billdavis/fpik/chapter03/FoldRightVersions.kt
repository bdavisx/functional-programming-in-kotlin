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

@RunWith(KTestJUnitRunner::class)
class Chapter3_7Tests: FeatureSpec() {
    init {
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
