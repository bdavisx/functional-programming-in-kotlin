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

fun <A,B> map(list: MyList<A>, f: (A) -> B): MyList<B> {
    return Nil
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
    }
}
