package ws.billdavis.fpik.chapter03

import io.kotlintest.KTestJUnitRunner
import io.kotlintest.specs.FeatureSpec
import org.junit.runner.RunWith

tailrec fun <A,B> foldLeft(list: MyList<A>, zero: B, f: (B,A) -> B): B = when(list) {
    is Nil -> zero
    is Cons -> foldLeft(list.tail, f(zero, list.head), f)
}

fun sumFL(ds: MyList<Int>): Int = foldLeft(ds, 0, {b, a -> a+b})
fun productFL(ds: MyList<Double>): Double = foldLeft(ds, 1.0, {b, a -> a*b})
fun <A> lengthFL(list: MyList<A>): Int = foldLeft(list, 0, {a, b -> a+1})

fun <A> reverse(list: MyList<A>): MyList<A> = when(list) {
    is Nil -> Nil
    is Cons -> foldLeft(list.tail, Cons(list.head, Nil), { b, a -> Cons(a,b)})
}

@RunWith(KTestJUnitRunner::class)
class FoldLeftVersionsTests: FeatureSpec() {
    init {
        feature("reverse MyList<A>") {
            scenario("Nil should return Nil") {
                reverse(Nil) shouldBe Nil
            }
            scenario("Correctly reverse lists") {
                reverse(myListOf(5)) shouldBe myListOf(5)
                reverse(myListOf(1,2,3)) shouldBe myListOf(3,2,1)
            }
        }
        feature("sumFL MyList<A>") {
            scenario("Nil should return 0") {
                sumFL(Nil) shouldBe 0
            }
            scenario("Correctly sum values") {
                sumFL(myListOf(5)) shouldBe 5
                sumFL(myListOf(1,5,7)) shouldBe 13
            }
        }
        feature("lengthFL MyList<A>") {
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
