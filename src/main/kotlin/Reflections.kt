import kotlin.reflect.*
import kotlin.reflect.full.*
//import kotlin.reflect.KClass
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


fun main() {
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