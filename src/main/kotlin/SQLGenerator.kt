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
        TODO("Not yet implemented")
    }

    override fun mapObject(o: Any?): String {
        TODO("Not yet implemented")
    }
}

class SQLGenerator(val typeMapping: TypeMapping) {

    fun createTable(c: KClass<*>): String {
        return  "CREATE TABLE " +
                c.simpleName + " (" +
                c.declaredMemberProperties.joinToString(", ") {
                            it.name + " " +
                            typeMapping.mapType(it.returnType as KClass<*>) + " " +
                            if (it.returnType.isMarkedNullable) "NULL" else "NOT NULL"
                } + ")"
    }

    fun insertSQLInto(o: Any): String {
        TODO("Not yet implemented")
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
}

fun main() {
    val s = Student(7, "Alex", StudentType.Doctoral)
    //println(createSQLTable(Student::class))
    //println(createSQLTable(s::class))
    println(insertSQLInto(s))
}