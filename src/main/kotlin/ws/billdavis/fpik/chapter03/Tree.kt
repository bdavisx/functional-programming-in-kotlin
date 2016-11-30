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

    fun depth(): Int {
        tailrec fun loop(tree: Tree<A>, currentDepth: Int, maximumDepth: Int): Int = when (tree) {
                is Tree.Leaf<A> -> Math.max(currentDepth, maximumDepth)
                is Tree.Branch<A> ->
                    loop(tree.left, currentDepth+1, loop(tree.right, currentDepth+1, maximumDepth))
            }

        return loop(this, 1, 1)
    }
}

fun <A: Comparable<A>> Tree<A>.maximum(): A {
    tailrec fun loop(tree: Tree<A>, currentMaximum: A?): A {
        return when (tree) {
            is Tree.Leaf<A> -> when(currentMaximum) {
                null -> tree.value
                else -> if (tree.value.compareTo(currentMaximum) < 0) currentMaximum else tree.value
            }
            is Tree.Branch<A> -> loop(tree.left,loop(tree.right, currentMaximum))
        }
    }
    return loop(this, null)
}

@RunWith(KTestJUnitRunner::class)
class TreeTests: FeatureSpec() {
    init {
        feature("tree.depth") {
            scenario("depth leaf s/b 1") {
                val tree: Tree<Int> = Tree.Leaf(10)
                tree.depth() shouldBe 1
            }
            scenario("depth of multiple branches/trees s/b correct") {
                val tree: Tree<Int> =
                    Tree.Branch(
                        Tree.Leaf(1),
                        Tree.Branch(
                            Tree.Leaf(2),
                            Tree.Leaf(5)))

                tree.depth() shouldBe 3
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
        feature("maximum tree") {
            scenario("max of single leaf s/b value iof that leaf") {
                val tree: Tree<Int> = Tree.Leaf(10)
                tree.maximum() shouldBe 10
            }
            scenario("maximum of multiple branches/trees s/b correct") {
                val tree: Tree<Int> =
                    Tree.Branch(
                        Tree.Leaf(1),
                        Tree.Branch(
                            Tree.Leaf(2),
                            Tree.Leaf(3)))

                tree.maximum() shouldBe 3
            }
        }
    }
}
