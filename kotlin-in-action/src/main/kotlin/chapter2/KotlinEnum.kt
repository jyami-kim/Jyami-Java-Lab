package chapter2

/**
 * Created by jyami on 2021/01/23
 */
enum class Color(
    val r: Int, val g: Int, val b: Int
) {
    RED(255, 0, 0), ORANGE(255, 165, 0),
    YELLOW(255, 255, 0), GREEN(0, 255, 0),
    BLUE(0, 0, 255), INDIGO(75, 0, 130), VIOLET(238, 130, 238);

    fun rgb() = (r * 256 + g) * 256 + b


}

fun getMnemonic(color: Color) =
    when(color){
        Color.RED -> "A"
        Color.ORANGE -> "B"
        Color.YELLOW -> "C"
        Color.GREEN -> "D"
        Color.BLUE -> "E"
        Color.INDIGO -> "F"
        Color.VIOLET -> "G"
    }

fun min(c1: Color, c2:Color) =
    when(setOf(c1, c2)){
        setOf(Color.RED, Color.YELLOW) -> Color.ORANGE
        setOf(Color.YELLOW, Color.BLUE) -> Color.GREEN
        setOf(Color.BLUE, Color.VIOLET) -> Color.INDIGO
        else -> throw Exception("Dirty Color")
    }

fun main(){
    println(getMnemonic(Color.BLUE))
}