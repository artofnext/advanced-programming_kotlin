
import kotlin.reflect.*
import kotlin.reflect.full.*
import kotlin.reflect.KClass
//import kotlin.reflect.full.declaredMemberProperties

class CounterBounded(max: Int) {
    val max: Int = max
    var value: Int = 0
        private set
    val isZero get() = value == 0

    constructor(): this(10)

    fun inc() {
        value++
    }
}

class Counter(max: Int) {
    val max: Int = max
    var value: Int = 0
        private set
    val isZero get() = value == 0

    constructor(): this(10)

    fun inc() {
        value++
    }
    fun dec() {
        value--
    }
}

fun main() {
    val clazz : KClass<Counter> = Counter::class
    val c = Counter(100)
//    println("Clas Name: " + clazz.simpleName)
//    println("Qualified Name: " + clazz.qualifiedName)
//    println("Visibility: " + clazz.visibility)
//
//    println("declaredMemberProperties: ")
//    clazz.declaredMemberProperties.forEach { println(it)}
//    println("declaredMemberFunctions: ")
//    clazz.declaredMemberFunctions.forEach { println(it)}
//
//    println("declaredMemberProperties: ")
//    clazz.declaredMemberProperties.forEach { println(it.name + ": " + it.returnType + " " + it.returnType.classifier + " " + it.returnType.isMarkedNullable)}
//
//    println(listOf(1,2,3).joinToString(separator = ";") {it.toString()})

    val s = Student(7, "Alex", StudentType.Doctoral)

    val clazz1: KClass<Student> = Student::class

    clazz1.declaredMemberProperties.forEach {
        println(it.name + ": " + it.call(s))
    }

    clazz.declaredMemberProperties.forEach {
        println(it.name + ": " + it.call(c))
    }

}

fun createTable(c: KClass<*>): String {
    var sql = "CREATE TABLE ..."

    return sql
}

fun insertInto(o: Any): String {
    val clazz: KClass<Any> = o::class as KClass<Any>
    return ""
}


fun main1() {
    val obj: Any = CounterBounded(10)
    val clazz: KClass<Any> = obj::class as KClass<Any>

    val propNames = clazz.declaredMemberProperties.map { it.name } // [isZero, max, value]
    println(propNames)

    val nConstructors = clazz.constructors.size // 2
    println(nConstructors)

    //val f: KFunction<*>? = clazz.declaredMemberFunctions.find { it.valueParameters.isEmpty() } // inc()

    //val propValues = clazz.declaredMemberProperties.map { Pair(it.name, it.call(counter)) } // [(isZero, true), (max, 10), (value, 0)]

    val c1 = clazz.primaryConstructor!!.call(10)  // equivalente: CounterBounded(10)
    val c2 = clazz.createInstance()           // equivalente: CounterBounded()

    val f: KFunction<*>? = clazz.declaredMemberFunctions.find { it.valueParameters.isEmpty() } // inc()
    //f?.call(counter)   // equivalente: counter.inc()


    data class ExampleDataClass(
        val name: String, var enabled: Boolean)

    ExampleDataClass::class.java.methods.forEach(::println)
}