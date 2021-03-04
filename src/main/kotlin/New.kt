
fun main() {
    println(facCycle(5))
    println(fac(5))
    println(facTal1(5, 1))
    println(facTal(5))

    // Lambda
    val par = {n: Int -> n % 2 == 0}
    println(par(5))

    val c = countIf(1, 10) { it % 2 == 0 }
    println(c)
    val d = countIf(1, 10, par)
    println(d)
}

fun facCycle(n: Int): Int {
    var f = 1
    for (i in 1 until n + 1)
        f *= i
    return f
}

// recursion

fun fac(n: Int): Int =
    if(n == 1) 1
    else n * fac(n - 1)

fun facTal1(n: Int, r: Int): Int =
    if(n == 1) r
    else facTal1(n - 1, r * n)

fun facTal(n: Int): Int {
    tailrec fun aux(n: Int, r: Int = 1): Int =
        if (n == 1) r
        else aux(n - 1, r * n)
    return aux(n, 1)
}

// Lambda

fun countIf(min: Int, max: Int, accept:(Int)->Boolean): Int =
    if(min > max) 0
    else if(accept(min)) 1 + countIf(min + 1, max, accept)
    else countIf(min + 1, max, accept)

