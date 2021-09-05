package dev.jeff.ktorapi

import io.ktor.application.*
import io.ktor.routing.*
import io.ktor.response.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module(testing: Boolean = false) {
    routing {
        get("/") {
            call.respondText("Hello, world!!")
        }
    }
}
