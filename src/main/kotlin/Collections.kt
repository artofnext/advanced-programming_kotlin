fun main() {

    val range = (1..10000)
    val data = List(10000) { range.random() }
    val timer = System.currentTimeMillis()
    unicList(data)
    println(System.currentTimeMillis() - timer)

    val timer1 = System.currentTimeMillis()
    unicList1(data)
    println(System.currentTimeMillis() - timer1)

    val set = mutableSetOf(1,2,3)  // hashtable
    set.contains(3) // constant
}

fun unicList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for(e in list) { //linear O(n)
        if(!result.contains(e)) // linear
            result.add(e)
    }
    return result
}

fun unicList1(list: List<Int>): Set<Int> { // run 10 - 15 times faster then unicList()
    val result = mutableSetOf<Int>()
    for(e in list) { //linear O(n)
        if(!result.contains(e)) // constant
            result.add(e)
    }
    return result


}