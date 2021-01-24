package chapter2

import java.lang.IllegalArgumentException

/**
 * Created by jyami on 2021/01/23
 */
interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr) : Int{
    if(e is Num){
        return e.value
    }
    if(e is Sum){
        return eval(e.left) + eval(e.right)
    }
    throw IllegalArgumentException("unknown expression")
}

fun evalWithLogging(e: Expr) : Int =
    when(e){
        is Num -> {
            println("num: ${e.value}")
            e.value
        }
        is Sum -> evalWithLogging(e.left) + evalWithLogging(e.right)
        else -> throw IllegalArgumentException("unknown expression")
    }