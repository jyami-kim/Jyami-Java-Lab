package chapter1

/**
 * Created by jyami on 2021/01/03
 */
data class Person (val name: String,
                   val age: Int ?= null)

fun main() {
    val persons = listOf(Person("민정"), Person(name = "쟈미", age = 24))
    val oldest = persons.maxBy { it.age ?: 0 }
    println("나이가 가장 많은 사람 : $oldest")
}