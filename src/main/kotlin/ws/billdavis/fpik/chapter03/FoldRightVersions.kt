package ws.billdavis.fpik.chapter03

import io.kotlintest.KTestJUnitRunner
import io.kotlintest.specs.FeatureSpec
import org.junit.runner.RunWith

fun <A,B> foldRight(list: MyList<A>, zero: B, f: (A,B) -> B): B = when(list) {
    is Nil -> zero
    is Cons -> f(list.head, foldRight(list.tail, zero, f))
}

fun productFR(ds: MyList<Double>): Double {
    return foldRight<Double, Double>(ds, 1.0, {a, b -> a*b})
}



@RunWith(KTestJUnitRunner::class)
class Chapter3_7Tests: FeatureSpec() {
    init {
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
