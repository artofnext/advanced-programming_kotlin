@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY)
annotation class TestCase

@TestCase
class Test {

    @TestCase
    val prop: Int = 0

    @TestCase
    fun test1() {

    }

    fun test2() {

    }

    fun test3() {

    }
}