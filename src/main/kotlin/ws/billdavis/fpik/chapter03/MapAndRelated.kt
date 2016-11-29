package ws.billdavis.fpik.chapter03

import io.kotlintest.KTestJUnitRunner
import io.kotlintest.specs.FeatureSpec
import org.junit.runner.RunWith

fun add1(list: MyList<Int>): MyList<Int> = when(list) {
    is Nil -> Nil
    is Cons -> Cons(list.head+1, add1(list.tail))
}

fun convertToString(list: MyList<Double>): MyList<String> = when(list) {
    is Nil -> Nil
    is Cons -> Cons(list.head.toString(), convertToString(list.tail))
}

fun <A,B> map(list: MyList<A>, f: (A) -> B): MyList<B> = when(list) {
    is Nil -> Nil
    is Cons -> Cons(f(list.head), map(list.tail, f))
}


fun <A,B> flatMap(list: MyList<A>, f: (A) -> MyList<B>): MyList<B> = when(list) {
    is Nil -> Nil
    is Cons -> concatenate<B>(myListOf(f(list.head), flatMap(list.tail, f)))
}

fun <A> filter(list: MyList<A>, f: (A) -> Boolean): MyList<A> = when(list) {
    is Nil -> Nil
    is Cons ->
        if(f(list.head)) { Cons(list.head, filter(list.tail, f)) }
        else { filter(list.tail, f) }
}

@RunWith(KTestJUnitRunner::class)
class MapAndRelatedTests: FeatureSpec() {
    init {
        feature("add1()") {
            scenario("add 1 to all items") {
                add1(myListOf(1,2,3,4)) shouldBe myListOf(2,3,4,5)
            }
        }
        feature("convertToString()") {
            scenario("convert all items to Strings") {
                convertToString(myListOf(1.1,2.2,3.3,4.4)) shouldBe
                    myListOf("1.1", "2.2", "3.3", "4.4")
            }
        }
        feature("map()") {
            scenario("add 1 to all items") {
                map(myListOf(1,2,3,4), { a -> a + 1 }) shouldBe myListOf(2,3,4,5)
            }
            scenario("convert all items to Strings") {
                map(myListOf(1.1,2.2,3.3,4.4), { a -> a.toString() }) shouldBe
                    myListOf("1.1", "2.2", "3.3", "4.4")
            }
        }
        feature("flatMap()") {
            // from exercise 3.20
            scenario("flatMap(List(1,2,3))(i => List(i,i)) == List(1,1,2,2,3,3)") {
                flatMap(myListOf(1,2,3), { i -> myListOf(i,i) }) shouldBe myListOf(1,1,2,2,3,3)
            }
        }
        feature("filter()") {
            scenario("handle nil") {
                filter(Nil as MyList<Int>, { a -> a % 2 == 0 }) shouldBe Nil
            }
            scenario("filter out odd numbers") {
                filter(myListOf(1, 2, 3, 4, 5, 6, 7, 8, 9), { a -> a % 2 == 0 }) shouldBe
                    myListOf(2, 4, 6, 8)
            }
        }
    }
}
