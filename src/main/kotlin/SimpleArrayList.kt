fun main() {

    val list = SimpleArrayList<String>()
    println(list.isEmpty)
    list.add("first")
    println(list.isEmpty)
    list.add("second")
    println(list.toString())
    println(list.size)
    println(list.get(1))
    println(list.contain("second"))
    val list1 = SimpleArrayList<String>()
    list1.add("first list2")
    list1.add("second list2")
    println(list1.toString())
    println(list1.size)
    val listMerged = list.merge(list1)
    println(listMerged.toString())
    println(listMerged.size)
}

class SimpleArrayList<T> {
    private var elements = Array<Any?>(10) { null }

    var size = 0
        private set

    var isEmpty = {size == 0}

    fun add(elem: Any?) {
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
}
