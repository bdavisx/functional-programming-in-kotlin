package ws.billdavis.fpik.chapter03

import io.kotlintest.KTestJUnitRunner
import io.kotlintest.specs.FeatureSpec
import org.junit.runner.RunWith

fun add1(list: MyList<Int>): MyList<Int> = when(list) {
    is Nil -> Nil
    is Cons -> Cons(list.head+1, add1(list.tail))
}


@RunWith(KTestJUnitRunner::class)
class MapAndRelatedTests: FeatureSpec() {
    init {
        feature("add1()") {
            scenario("add 1 to all items") {
                add1(myListOf(1,2,3,4)) shouldBe myListOf(2,3,4,5)
            }
        }
    }
}
