package ws.billdavis.fpik.chapter03

import io.kotlintest.KTestJUnitRunner
import io.kotlintest.specs.FeatureSpec
import org.junit.runner.RunWith

sealed class Tree<out A> {
    data class Leaf<out A>(val value: A):Tree<A>()
    data class Branch<out A>(val left: Tree<A>, val right: Tree<A>):Tree<A>()

    fun size(): Int = when (this) {
        is Leaf<*> -> 1
        is Branch<*> -> 1 + left.size() + right.size()
    }
}

@RunWith(KTestJUnitRunner::class)
class TreeTests: FeatureSpec() {
    init {
        feature("Tree") {
            scenario("single leaf s/b 1") {
                val tree: Tree<Int> = Tree.Leaf(1)
                tree.size() shouldBe 1
            }
            scenario("multiple branches/trees s/b correct") {
                val tree: Tree<Int> =
                    Tree.Branch(
                        Tree.Leaf(1),
                        Tree.Branch(
                            Tree.Leaf(2),
                            Tree.Leaf(1)))

                tree.size() shouldBe 5
            }
        }
    }
}
