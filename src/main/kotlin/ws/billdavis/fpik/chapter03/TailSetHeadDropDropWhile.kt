package ws.billdavis.fpik.chapter03

import io.kotlintest.KTestJUnitRunner
import io.kotlintest.specs.FeatureSpec
import org.junit.runner.RunWith

fun <A> tail(list: MyList<A>): MyList<A> = when(list) {
    is Nil -> Nil
    is Cons -> list.tail
}

fun <A> setHead(list: MyList<A>, newHead: A): MyList<A> = when(list) {
    is Nil -> Cons(newHead, Nil)
    is Cons -> Cons(newHead, list.tail)
}

fun <A> drop(list: MyList<A>, numberOfItems: Int): MyList<A> = when(numberOfItems) {
    0 -> list
    else -> when(list) {
        is Nil -> Nil
        is Cons -> drop(list.tail, numberOfItems-1)
    }
}

@RunWith(KTestJUnitRunner::class)
class Chapter3_2Tests: FeatureSpec() {
    init {
        feature("tail MyList<A>") {
            scenario("Nil should return Nil") {
                tail(Nil) shouldBe Nil
            }

            scenario("Single item list should return Nil") {
                tail(myListOf(1)) shouldBe Nil
            }

            scenario("should return correctly with multi-element list") {
                tail(myListOf(1,2,3)) shouldBe myListOf(2,3)
            }
        }
        feature("setHead MyList<A>, value") {
            scenario("Nil should return single item") {
                setHead(Nil, 1) shouldBe myListOf(1)
            }

            scenario("Single item list should return new item") {
                setHead(myListOf(2), 1) shouldBe myListOf(1)
            }

            scenario("should return correctly with multi-element list") {
                setHead(myListOf(1,2,3), 4) shouldBe myListOf(4,2,3)
            }
        }
        feature("drop MyList<A>, numberOfItems") {
            scenario("Nil should return nill") {
                drop(Nil, 1) shouldBe Nil
                drop(Nil, 5) shouldBe Nil
            }

            scenario("Single item list should return Nil") {
                drop(myListOf(2), 1) shouldBe Nil
                drop(myListOf(2), 5) shouldBe Nil
            }

            scenario("should return correctly with multi-element list") {
                drop(myListOf(1,2,3,4), 2) shouldBe myListOf(3,4)
            }
        }
    }
}
