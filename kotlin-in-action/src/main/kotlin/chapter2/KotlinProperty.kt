package chapter2

/**
 * Created by jyami on 2021/01/23
 */
class Person(
    val name: String,
    var isMarried: Boolean
)

class Rectangle(val height: Int, val width: Int){
    val isSquare: Boolean
        get(){
            return height == width
        }
}

fun main(){
    val person = Person("jyami", false)
    println(person.name)
    person.isMarried = false
}

