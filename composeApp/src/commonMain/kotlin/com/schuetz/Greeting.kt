package com.schuetz

class Greeting {
    fun greet(): String {
        return "Hello"
    }

    fun greetPrint(parameter: String) {
        println("Hello $parameter")
    }
}

object GreetingObj {
    fun greetPrintFunctionInObj() {
        println("Hello obj function")
    }
}

fun greetPrintFunction() {
    println("Hello function")
}
