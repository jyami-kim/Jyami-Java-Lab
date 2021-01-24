package chapter2

import java.io.BufferedReader
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException

/**
 * Created by jyami on 2021/01/23
 */

val number = 1

val percentage =
    if (number in 0..10) number
    else throw IllegalArgumentException("A percentage value must be between 0 and 100 : $number")

fun readNumber(reader: BufferedReader) : Int? {
    try{
        val line = readLine()
        return Integer.parseInt(line)
    }catch (e: NumberFormatException){
        return null
    }finally {
        reader.close()
    }
}

fun readNumber2(reader: BufferedReader) : Int? = try{
        val line = readLine()
        Integer.parseInt(line)
    }catch (e: NumberFormatException){
        null
    }finally {
        reader.close()
    }