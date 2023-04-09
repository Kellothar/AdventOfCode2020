package day03

import asResource

fun day3part1(): Int {
    return "Day3.input".asResource { file ->
        val step = TreeStep(3, 1)
        val field = TreeField(file.lines(), step)
        var trees = 0
        for (line in field) {
            if (line.isTree()) trees++
        }
        trees
    }
}

fun day3part2(): Int {
    return "Day3.input".asResource { file ->
        val lines = file.lines()
        var accumulator = 1
        for (step in arrayOf(
            TreeStep(1, 1),
            TreeStep(3, 1),
            TreeStep(5, 1),
            TreeStep(7, 1),
            TreeStep(1, 2)
        )) {
            val field = TreeField(lines, step)
            var trees = 0
            for (line in field) {
                if (line.isTree()) trees++
            }
            accumulator *= trees
        }
        accumulator
    }
}


data class TreeStep(val right: Int, val down: Int)
data class TreeField(val lines: List<String>, val step: TreeStep) : Iterable<TreeLine> {
    override fun iterator(): Iterator<TreeLine> {
        return object : Iterator<TreeLine> {
            var line = 0
            var position = 0

            override fun hasNext() =
                line + step.down < lines.size

            override fun next(): TreeLine {
                position += step.right
                line += step.down
                return TreeLine(lines[line], position)
            }
        }
    }
}

data class TreeLine(val line: String, val position: Int) {
    private val tree = '#'

    fun isTree(): Boolean {
        val index = if (position < line.length) position else position % line.length
        return line[index] == tree
    }
}