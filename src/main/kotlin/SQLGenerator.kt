import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties

data class Student(
    val number: Int,
    val name: String,
    val type: StudentType? = null
)

enum class StudentType {
    Bachelor, Master, Doctoral
}

// 1. CREATE TABLE
// Return string with SQL request for create table based on class provided as argument
fun createSQLTable(c: KClass<*>): String {
    var sql = "CREATE TABLE "
    sql += c.simpleName // will be table name
    sql += " ("
//    c.declaredMemberProperties.forEach {
//        sql += it.name + " " + typeCast(it.returnType.toString()) +
//                " " + if (it.returnType.isMarkedNullable) "NULL," else "NOT NULL,"
//    }

    sql += c.declaredMemberProperties.joinToString(", ") {
                it.name + " " +
                typeCast(it.returnType.toString()) + " " +
                if (it.returnType.isMarkedNullable) "NULL" else "NOT NULL"
    }
    sql += ")"

    // CREATE TABLE Student (number INT NOT NULL, name VARCHAR(255) NOT NULL, type VARCHAR(8));
    return sql
}

fun typeCast(t: String): String {
    println(t)
    return when (t) {
        "kotlin.Int" -> "INT"
        "kotlin.String" -> "VARCHAR(50)"
        "StudentType?" -> "VARCHAR(8)"
        // todo add more
        else -> { // Note the block
            "HZ"
        }
    }
}

// 2. INSERT INTO
// Return string with SQL request for populate table with class object data values
fun insertSQLInto(o: Any): String {
    val clazz: KClass<Any> = o::class as KClass<Any>

    // INSERT INTO Student (name, number, type) VALUES ('Alex', 7, 'Doctoral')
    return "INSERT INTO " +
            clazz.simpleName + " (" +
            clazz.declaredMemberProperties.joinToString(", ") { it.name } + ") VALUES (" +
            clazz.declaredMemberProperties.joinToString(", ") {
                val propValue = it.call(o)
                if (propValue is String || propValue is Enum<*>)
                    "'$propValue'"
                else propValue.toString()
            } + ")"
}

//3. SQL technology variability

interface TypeMapping {
    fun mapType(c: KClass<*>): String
    fun mapObject(o: Any?): String
}

class mySQL: TypeMapping {
    override fun mapType(c: KClass<*>): String {
        // number INT NOT NULL, name VARCHAR(255) NOT NULL, type VARCHAR(8)
        return when (c) {
            Int::class -> "INT"
            Boolean::class -> "BOOL"
            String::class -> "VARCHAR(50)"
            StudentType::class -> "VARCHAR(8)"
            // todo add more
            else -> { // Note the block
                "HZ"
            }
        }
    }

    override fun mapObject(o: Any?): String {
        //TODO("Not yet implemented")
        return "result"
    }
}

class myOracle: TypeMapping {
    override fun mapType(c: KClass<*>): String {
        return when (c) {
            Int::class -> "NUMBER"
            Boolean::class -> "BOOL"
            String::class -> "VARCHAR2(50)"
            StudentType::class -> "VARCHAR2(8)"
            // todo add more
            else -> { // Note the block
                "HZ"
            }
        }
    }

    override fun mapObject(o: Any?): String {
        TODO("Not yet implemented")
    }
}

class SQLGenerator(var typeMapping: TypeMapping) {

    fun createTable(c: KClass<*>): String {

// Oracle       CREATE TABLE Student(number NUMBER NOT NULL, name VARCHAR2(50) NOT NULL, type VARCHAR2(8) NULL);

// SQL          CREATE TABLE Student (number INT NOT NULL, name VARCHAR(255) NOT NULL, type VARCHAR(8));

        return  "CREATE TABLE " +
                c.simpleName + " (" +
                c.declaredMemberProperties.joinToString(", ") {
                            it.name + " " +
                            typeMapping.mapType(it.returnType.classifier as KClass<*>) + " " +
                            if (it.returnType.isMarkedNullable) "NULL" else "NOT NULL"
                } + ")"
    }

    fun insertSQLInto(o: Any): String {
        TODO("Not yet implemented")
        val clazz: KClass<Any> = o::class as KClass<Any>
        // INSERT INTO Student (name, number, type) VALUES ('Alex', 7, 'Doctoral')

        // INSERT INTO emp (empno, ename, job, sal, comm, deptno)
        // VALUES (4160, 'STURDEVIN', 'SECURITY GUARD', 2045, NULL, 30);

        return "INSERT INTO " +
                clazz.simpleName + " (" +
                clazz.declaredMemberProperties.joinToString(", ") { it.name } + ") VALUES (" +
                clazz.declaredMemberProperties.joinToString(", ") {
                    val propValue = it.call(o)
                    if (propValue is String || propValue is Enum<*>)
                        "'$propValue'"
                    else propValue.toString()
                } + ")"
    }
}

fun main() {
    val s = Student(7, "Alex", StudentType.Doctoral)
    //println(createSQLTable(Student::class))
    //println(createSQLTable(s::class))
//    println(insertSQLInto(s))


    val myGenerator = SQLGenerator(mySQL())

    println(myGenerator.createTable(Student::class))

    myGenerator.typeMapping = myOracle()
    println(myGenerator.createTable(Student::class))

}