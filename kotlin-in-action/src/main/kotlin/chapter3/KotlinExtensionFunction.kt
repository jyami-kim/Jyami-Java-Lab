package chapter3

/**
 * Created by jyami on 21. 2. 11.
 */
open class View{
    open fun click() = println("View Clicked")
}
class Button : View() {
    override fun click() = println("Button cliecked")
}

fun View.showOff() = println("I'm a View")
fun Button.showOff() = println("I'm a Button")