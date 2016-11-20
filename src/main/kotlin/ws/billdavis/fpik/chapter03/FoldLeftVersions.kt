package ws.billdavis.fpik.chapter03

import io.kotlintest.KTestJUnitRunner
import io.kotlintest.specs.FeatureSpec
import org.junit.runner.RunWith

fun <A,B> foldLeft(list: MyList<A>, zero: B, f: (B,A) -> B): B = when(list) {
    is Nil -> zero
    is Cons -> foldLeft(list.tail, f(zero, list.head), f)
}

fun productFL(ds: MyList<Double>): Double = foldLeft(ds, 1.0, {b, a -> a*b})

fun <A> lengthFL(list: MyList<A>): Int = foldLeft(list, 0, {a, b -> a+1})

@RunWith(KTestJUnitRunner::class)
class Chapter3_10Tests: FeatureSpec() {
    init {
        feature("length MyList<A>") {
            scenario("Nil should return 0") {
                lengthFL(Nil) shouldBe 0
            }
            scenario("single element should return 1") {
                lengthFL(myListOf(1)) shouldBe 1
            }
            scenario("multi element list should return correct length") {
                lengthFL(myListOf(1,2,3,4)) shouldBe 4
            }
        }
        feature("productFR MyList<A>") {
            scenario("Nil should return 1.0") {
                productFL(Nil) shouldBe (1.0 plusOrMinus 0.0)
            }
            scenario("Correctly multiply values") {
                productFL(myListOf(5.0)) shouldBe (5.0 plusOrMinus 0.0)
                productFL(myListOf(2.0, 5.0, 7.0)) shouldBe (70.0 plusOrMinus 0.0)
            }
        }
    }
}
