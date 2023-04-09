package day01

import asResource

val target = 2020

fun day1part1(): Int {
    return "Day1.input".asResource { file ->
        val numbers = file.lines().map { it.toInt() }
        val input = numbers.sorted()
        for (first in input) {
            for (lastIndex in 0..input.lastIndex) {
                val last = input[input.lastIndex - lastIndex]
                if (first + last == target) {
                    return@asResource first * last
                } else if (first + last < target) {
                    break
                }
            }
        }
        0
    }
}

fun day1part2(): Int {
    return "Day1.input".asResource { file ->
        val numbers = file.lines().map { it.toInt() }
        val input = numbers.sorted()
        val index = IndexStorage(input.lastIndex)

        do {
            val first = input[index.first]
            val second = input[index.second]
            val third = input[index.third]
            if (first + second + third == target) {
                return@asResource first * second * third
            }
        } while (index.nexStep())
        0
    }
}

class IndexStorage(val lastIndex: Int) {
    var first = 0
        private set
    var second = first + 1
        private set
    var third = lastIndex
        private set

    fun nexStep(): Boolean {
        third--
        if (third == second) {
            second++
            third = lastIndex
            if (second == lastIndex) {
                first++
                second = first + 1
            }
        }
        return second != lastIndex
    }
}

fun day1part1_(): Int {
    return "Day1.input".asResource { file ->
        val numbers = file.lines().map { it.toInt() }
        numbers.findPairOfSum(target)?.let { (a, b) -> a * b } ?: 0
    }
}

fun day1part2_(): Int {
    return "Day1.input".asResource { file ->
        val numbers = file.lines().map { it.toInt() }
        numbers.findTripleOfSum(target)?.let { (a, b, c) -> a * b * c } ?: 0
    }
}

fun List<Int>.findPairOfSum(sum: Int): Pair<Int, Int>? {
    val complements = associateBy { sum - it }
    return firstNotNullOfOrNull { number ->
        val complement = complements[number]
        if (complement != null) Pair(number, complement) else null
    }
}

fun List<Int>.findTripleOfSum(sum: Int): Triple<Int, Int, Int>? =
    firstNotNullOfOrNull { x ->
        findPairOfSum(sum - x)?.let { pair ->
            Triple(x, pair.first, pair.second)
        }
    }