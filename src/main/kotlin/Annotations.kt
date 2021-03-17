import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation

@Target(AnnotationTarget.FUNCTION)
annotation class TestCase (val desc: String, val code: Int = -1)

@Target(AnnotationTarget.CLASS)
annotation class TestSuite

@TestSuite
class Tests {


    val prop: Int = 0

    @TestCase ("desc...", 4)
    fun test1() {

    }

    @TestCase ("desc...")
    fun test2() {

    }

    @Deprecated("old")
    fun test3() {

    }
}



fun main() {
    val clazz = Tests::class
    println(clazz.annotations)

    clazz.declaredMemberFunctions.forEach {
        val hasAnn = it.hasAnnotation<TestCase>()
        if(hasAnn) {
            val ann = it.findAnnotation<TestCase>()
            println(it)
//            println(ann!!.desc)
//          println(ann?.desc)   // shows null
          println(ann?.desc ?: "")  // workaround null
        }
    }

    val c = String::class

//    " ".strip()

        c.declaredFunctions.filter { it.hasAnnotation<Deprecated>() }.forEach { println(it)}

}