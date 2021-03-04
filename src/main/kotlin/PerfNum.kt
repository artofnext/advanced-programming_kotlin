

// val s = sumRangeRec(1, 5) // 15

// 1 recursion
fun sumRangeRec(a: Int, b: Int): Int =
    if (a == b) b
    else a + sumRangeRec(a + 1, b)

// 2 tail recursion
fun sumRange(min: Int = 1, max: Int): Int {
    tailrec fun aux(a: Int, b: Int, res: Int): Int =
        if (a > b) res
        else aux(a + 1, b, a + res)
    return aux(min, max, 0)
}

// 3 lambdas
val odd = { a: Int -> a % 2 != 0 }
val even = { a: Int -> a % 2 == 0 }
val divideBy10 = { a: Int -> a % 10 == 0 }
fun isDivided(b: Int, a: Int): Boolean = b % a == 0


// 4 Higher order function
fun sumRangeIf(a: Int, b: Int, condition: (Int)->Boolean): Int =
        if (a > b) 0
        else if (condition(a)) 1 + sumRangeIf(a + 1, b, condition)
        else sumRangeIf(a + 1, b, condition)

// Functional composition
fun isPerfectNumber(n: Int): Boolean {
    tailrec fun aux(n: Int, sum: Int = 0, init: Int = 1): Int =
        if (init >= n) sum
        else if (isDivided(n, init)) aux(n, sum + init, init + 1 )
        else aux(n, sum, init + 1)
    return aux(n) == n
}

fun main() {
    println(sumRange(1, 5))

    println(odd(5))
    println(even(5))
    println(divideBy10(20))

    println(sumRangeIf(0, 100, divideBy10))

    println("List of perfect numbers")
    (1..1000)
        .filter { n -> isPerfectNumber(n) }
        .forEach { n -> println(n) }
}