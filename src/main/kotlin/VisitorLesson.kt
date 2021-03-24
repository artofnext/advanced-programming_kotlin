

abstract class Element()

class Leaf: Element()

class Composite: Element() {
    val children = mutableListOf<Element>()
}

fun countComposies(c: Composite): Int {
    var count = 0
    c.children.forEach {
        if (it is Composite) {
            count++
            count += countLeafs(it)
        }
    }
    return count
}

fun countLeafs(c: Composite): Int {
    var count = 1
    c.children.forEach {
        if(it is Leaf)
            count ++
        else if(it is Composite)
            count += countLeafs(it)
    }
    return count
}

fun main() {
    val a = Composite()
    a.children.add(Leaf())
    a.children.add(Leaf())
    a.children.add(Leaf())
    a.children.add(Leaf())
    a.children.add(Leaf())

    val b = Composite()
    a.children.add(b)

    b.children.add(Leaf())

    println(countLeafs(a))
    println(countComposies(a))
}