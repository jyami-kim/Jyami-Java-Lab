package chapter3

/**
 * Created by jyami on 21. 2. 11.
 */
val String.lastChar: Char
    get() = get(length - 1)

fun main(args: Array<String>){
    val kotlinLogo = """|  //
                        .| //
                        .|/ \\""".trimMargin(".")
    println(kotlinLogo)
}