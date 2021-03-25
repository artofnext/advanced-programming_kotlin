

abstract class Element(val name: String) {

    abstract fun accept(v: Visitor)
}

interface Visitor {
    fun visit(c: Composite): Boolean = true
    fun visit(l: Leaf) {}
}

class MyVisitor: Visitor {

    override fun visit(l: Leaf) {
        TODO("Not yet implemented")
    }

}

class Leaf(name: String): Element(name) {
    override fun accept(v: Visitor) {
        TODO("Not yet implemented")
    }
}

class Composite(name: String): Element(name) {
    val children = mutableListOf<Element>()
    override fun accept(v: Visitor) {
        TODO("Not yet implemented")
    }
}

fun countComposies(c: Composite): Int {
    var count = 0
    c.children.forEach {
        if (it is Composite) {
            count++
            count += countLeaves(it)
        }
    }
    return count
}

fun countLeaves(c: Composite): Int {
    var count = 1
    c.children.forEach {
        if(it is Leaf)
            count ++
        else if(it is Composite)
            count += countLeaves(it)
    }
    return count
}

fun main() {
    val a = Composite("1")
    a.children.add(Leaf("a"))
    a.children.add(Leaf("b"))
    a.children.add(Leaf("c"))
    a.children.add(Leaf("d"))
    a.children.add(Leaf("e"))

    val b = Composite("2")
    a.children.add(b)

    b.children.add(Leaf("f"))

    println(countLeaves(a))
    println(countComposies(a))
}