package com.happiestminds.remainder

var listRemainder = mutableListOf<Remainder>()

data class Remainder(var title: String, var description: String, var date: String, var time: String) {
    override fun toString(): String {
        return """
            Title : $title
            Description : $description
            date : $date
            Time : $time
        """.trimIndent()
    }
}
