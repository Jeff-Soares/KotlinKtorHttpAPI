package dev.jeff.ktorapi

import dev.jeff.ktorapi.routes.registerCustomerRoutes
import dev.jeff.ktorapi.routes.registerOrderRoutes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module(testing: Boolean = false) {
    install(CORS) {
        anyHost()
    }

    install(ContentNegotiation){
        json()
    }
    registerCustomerRoutes()
    registerOrderRoutes()

    // Just for test
    routing{
        get("/"){
            call.respondText("Hello world!")
        }
    }
}
