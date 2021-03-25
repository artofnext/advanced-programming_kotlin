

abstract class Elem(val name: String) {
    var depth = 0

    var parent: DirElem? = null

    // receive parent's depth
    abstract fun updateDepth(value: Int)



}

class FileElem(name: String): Elem(name) {

    override fun updateDepth(value: Int) {
        this.depth = value + 1
    }
}

class DirElem(name: String): Elem(name) {

    var content = mutableListOf<Elem>()

    var nElements = 0

    override fun updateDepth(value: Int) {
        this.depth = value + 1
        this.content.forEach {
            it.updateDepth(this.depth)
        }
    }

    fun updateNElements() {
        this.nElements =
    }

    fun addElem(e: Elem) {
        content.add(e)
        if(e.parent != null) {
            e.parent!!.content.remove(e)
        }
        e.updateDepth(this.depth)
        e.parent = this
        nElements = content.size
    }
}

fun main() {

    val dir1 = DirElem("Directory1")

    (1..5).forEach {
        dir1.addElem(FileElem("File$it"))
    }

    val dir2 = DirElem("Directory2")

    (6..9).forEach {
        dir2.addElem(FileElem("File$it"))
    }

    val dir0 = DirElem("Directory0")

    dir0.addElem(dir1)
    dir1.addElem(dir2)

    println(dir0.depth)
    println(dir1.depth)
    println(dir2.depth)
    println(dir2.content[0].depth)

    dir0.addElem(dir2)
    println(dir2.depth)
    println(dir2.content[0].depth)




}


