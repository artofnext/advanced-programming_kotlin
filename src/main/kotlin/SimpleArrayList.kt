fun main() {
    //test1()

    test2()
}

fun test1() {
    val list = SimpleArrayList<String>()
    println("=====Empty list - list.isEmpty")
    println(list.isEmpty)
    list.add("first")
    println("=====Added 1 element - list.isEmpty")
    println(list.isEmpty)
    list.add("second")
    println("=====Added 2 elements - list.toString")
    println(list.toString())
    println("=====Added 2 elements - list.size")
    println(list.size)
    println("=====Added 2 elements - list.get(1)")
    println(list.get(1))
    println("=====Added 2 elements - list.contain(\"second\")")
    println(list.contain("second"))
    val list1 = SimpleArrayList<String>()
    list1.add("first list2")
    list1.add("second list2")
    println("=====New list1 with 2 elements - list1.toString")
    println(list1.toString())
    println("=====- list1.size")
    println(list1.size)
    val listMerged = list.merge(list1)
    println("=====val listMerged = list.merge(list1)")
    println(listMerged.toString())
    println("=====- listMerged.size")
    println(listMerged.size)
}

fun test2 () {
    val list = SimpleArrayList<Int>()
    list.add(12)
    list.add(122)
    list.add(2)
    list.add(2)
    list.add(2)
    list.add(2)
    list.add(48)
    list.add(49)
    list.add(38)

    // Todo ??? Problem with comparison in lambda - when use < or > get type mismatch Unit - Boolean ???
    val c = list.count { a: Any? -> a == 2 }
    println(c)
}

class SimpleArrayList<T> {
    private var elements = Array<Any?>(10) { null }

    var size = 0
        private set

    var isEmpty = {size == 0}

    fun add(elem: Any?) {
        check(elem == null) {"can't add null"}
        if (size == elements.size)
            elements = elements.copyOf(elements.size + 10)
        elements[size] = elem
        size ++
    }

    override fun toString(): String {
        var result = ""
        for (i in 0 until size) {
            result = result + ", " + elements.get(i)
        }
        return result
    }

    fun get(index: Int): Any? {
        check(index in 0 until size) {"index out of range"}
        return elements[index]
    }

    fun contain(elem: Any?): Boolean {
        return elements.contains(elem)
    }

    fun merge(list: SimpleArrayList<T>): SimpleArrayList<T> {
        for (i in 0 until list.size) {
            this.add(list.get(i))
        }
        return this
    }

    fun count(accept: (Any?) -> Boolean): Int {
        var counter: Int = 0
        for (i in 0 until this.size) {
            if (accept(this.get(i))) counter++
        }
        return counter
    }
}
