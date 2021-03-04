import java.io.File

fun main() {
    val dir = File("C:/Users/Iryna/IdeaProjects/kotlinPA2")
    val dirList = dirFilesRecursive(dir, includeFile)
    println("Recursive")
    dirList.forEach { (println(it)) }

    println("Recursive + High order function")
    val dirList1 = dirFilesRecursive(dir, includeFileEnds(".xml"))
    dirList1.forEach { (println(it)) }

    println("Extension + High order function")
    dir.dirFilesRec(includeFileEnds(".jar")).forEach {
        println(it)
    }

    println("Extension1 + High order function")
    dir.dirFilesRec1(includeFileEnds(".jar")).forEach {
        println(it)
    }
    // println(dirList)
}

fun dirFiles(dir: File): List<File> {
    val files = mutableListOf<File>()
    dir.listFiles().forEach {
        if (it.isFile)
            files.add(it)
    }
    return files
}

val includeFile = { file: File -> file.name.endsWith(".kt") }

// High order function returns function
fun includeFileEnds(ending: String): (File) -> Boolean = { file: File -> file.name.endsWith(ending) }

fun dirFilesRecursive(dir: File, accept: (File) -> Boolean = { true }): List<File> {
    fun aux(dir: File): List<File> {
        val files = mutableListOf<File>()
        if (dir.listFiles() != null) {
            dir.listFiles().forEach {
                if (it.isFile && accept(it))
                    files.add(it)
                else
                    files.addAll(aux(it))
            }
        }
        return files
    }
    return aux(dir)
}

// Extension function
fun File.dirFilesRec(accept: (File) -> Boolean = { true }): List<File> {
    fun aux(dir: File): List<File> {
        val files = mutableListOf<File>()
        if (dir.listFiles() != null) {
            dir.listFiles().forEach {
                if (it.isFile && accept(it))
                    files.add(it)
                else
                    files.addAll(aux(it))
            }
        }
        return files
    }
    return aux(this)
}

// Extension function
fun File.dirFilesRec1(accept: (File) -> Boolean = { true }): List<File> {
        val files = mutableListOf<File>()
        if (this.listFiles() != null) {
            this.listFiles().forEach {
                if (it.isFile && accept(it))
                    files.add(it)
                else
                    files.addAll(it.dirFilesRec1(accept))
            }
        }
        return files


}
