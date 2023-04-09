package day02

import asResource

fun day2part1(): Int {
    return "Day2.input".asResource { file ->
        file.lines().count { checkPassword(it, ::validatePassword1) }
    }
}

fun day2part2(): Int {
    return "Day2.input".asResource { file ->
        file.lines().count { checkPassword(it, ::validatePassword2) }
    }
}

fun checkPassword(input: String, scheme: (PasswordPolicy, String) -> Boolean): Boolean {
    val split = input.split(":")
    val policy = makePasswordPolicy(split.first().trim())
    val password = split.last().trim()
    return scheme(policy, password)
}

fun validatePassword1(policy: PasswordPolicy, password: String): Boolean {
    val count = password
        .chars()
        .filter { it == policy.symbol }
        .count()
    return count >= policy.first && count <= policy.second
}

fun validatePassword2(policy: PasswordPolicy, password: String): Boolean {
    return policy.first <= password.length
            && policy.second <= password.length
            && (password[policy.first - 1].code == policy.symbol)
        .xor(password[policy.second - 1].code == policy.symbol)
}


data class PasswordPolicy(val first: Int, val second: Int, val symbol: Int)

private val PasswordRegex = Regex("""(\d+)-(\d+) ([a-z])""")

fun makePasswordPolicy(raw: String): PasswordPolicy =
    PasswordRegex.matchEntire(raw)!!
        .destructured
        .let { (min, max, symbol) ->
            PasswordPolicy(min.toInt(), max.toInt(), symbol.chars().findFirst().asInt)
        }
