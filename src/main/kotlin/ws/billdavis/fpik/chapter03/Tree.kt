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

tailrec fun maximum(tree: Tree<Int>): Int {
    fun loop(tree: Tree<Int>, currentMaximum: Int): Int {
        return when (tree) {
            is Tree.Leaf<Int> -> Math.max(tree.value, currentMaximum)
            is Tree.Branch<Int> -> loop(tree.left,loop(tree.right, currentMaximum))
        }
    }
    return loop(tree, Int.MIN_VALUE)
}

@RunWith(KTestJUnitRunner::class)
class TreeTests: FeatureSpec() {
    init {
        feature("maximum(tree)") {
            scenario("max of single leaf s/b value iof that leaf") {
                val tree: Tree<Int> = Tree.Leaf(10)
                maximum(tree) shouldBe 10
            }
            scenario("maximum of multiple branches/trees s/b correct") {
                val tree: Tree<Int> =
                    Tree.Branch(
                        Tree.Leaf(1),
                        Tree.Branch(
                            Tree.Leaf(2),
                            Tree.Leaf(3)))

                maximum(tree) shouldBe 3
            }
        }
        feature("Tree.size") {
            scenario("size of single leaf s/b 1") {
                val tree: Tree<Int> = Tree.Leaf(1)
                tree.size() shouldBe 1
            }
            scenario("size of multiple branches/trees s/b correct") {
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
