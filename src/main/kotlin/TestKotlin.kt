
fun main() {
    println("Hello World!")

}

fun inc(n: Int, x: Int = 1) = n + 1

fun multiply(a: Double, b: Double) = a * b

fun square(input: Double): Int {
    var guess: Double = input / 2
    var newGuess: Double = 0.0
    var iterations: Int = 0
    while ( iterations < 100) {
        newGuess = (guess + input / guess) / 2
        println("New Guess")
        println(newGuess)
        if (guess == newGuess) break
        guess = newGuess
        iterations += 1
    }
    return iterations
}