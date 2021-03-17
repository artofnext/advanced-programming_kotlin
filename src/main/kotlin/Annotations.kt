@Target(AnnotationTarget.FUNCTION)
annotation class TestCase (val desc: String, val code: Int = -1)


class Test {


    val prop: Int = 0

    @TestCase ("desc...", 4)
    fun test1() {

    }

    @TestCase ("desc...")
    fun test2() {

    }

    fun test3() {

    }
}