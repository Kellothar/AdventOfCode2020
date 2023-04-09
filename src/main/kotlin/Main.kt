import day01.day1part1
import day01.day1part1_
import day01.day1part2
import day01.day1part2_
import day02.day2part1
import day02.day2part2
import day03.day3part1
import day03.day3part2

fun main(args: Array<String>) {
    println("Day 1 (part 1): ${day1part1()}")
    println("Day 1 (part 2): ${day1part2()}")
    println("Day 1` (part 1): ${day1part1_()}")
    println("Day 1` (part 2): ${day1part2_()}")
    println("Day 2 (part 1): ${day2part1()}")
    println("Day 2 (part 2): ${day2part2()}")
    println("Day 3 (part 1): ${day3part1()}")
    println("Day 3 (part 2): ${day3part2()}")
}

fun <R> String.asResource(work: (String) -> R): R {
//    val content = this.javaClass::class.java.getResource(this)!!.readText()
    val content = object {}.javaClass.getResource(this)!!.readText()
    return work(content)
}