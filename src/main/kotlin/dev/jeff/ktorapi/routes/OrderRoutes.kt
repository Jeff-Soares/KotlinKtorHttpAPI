package dev.jeff.ktorapi.routes

import dev.jeff.ktorapi.models.Order
import dev.jeff.ktorapi.models.OrderItem
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

val orderStorage = listOf(
    Order(
        "2020-04-06-01", listOf(
            OrderItem("Ham Sandwich", 2, 5.50),
            OrderItem("Water", 1, 1.50),
            OrderItem("Beer", 3, 2.30),
            OrderItem("Cheesecake", 1, 3.75)
        )
    ),
    Order(
        "2020-04-03-01", listOf(
            OrderItem("Cheeseburger", 1, 8.50),
            OrderItem("Water", 2, 1.50),
            OrderItem("Coke", 2, 1.76),
            OrderItem("Ice Cream", 1, 2.35)
        )
    )
)

fun Application.registerOrderRoutes() {
    routing{
        listOrdersRoute()
        getOrderRoute()
        totalOrderRoute()
    }
}

fun Route.listOrdersRoute() {
    get("/orders") {
        if (orderStorage.isNotEmpty()) {
            call.respond(orderStorage)
        } else {
            call.respondText("No orders found.", status = HttpStatusCode.NotFound)
        }
    }
}

fun Route.getOrderRoute() {
    get("/orders/{id}") {
        val id = call.parameters["id"] ?: return@get call.respondText(
            "Bad order number format", status = HttpStatusCode.BadRequest
        )
        val order = orderStorage.find { it.number == id } ?: return@get call.respondText(
            "Not Found",
            status = HttpStatusCode.NotFound
        )
        call.respond(order)
    }
}

fun Route.totalOrderRoute() {
    get("/orders/{id}/total") {
        val id = call.parameters["id"] ?: return@get call.respondText(
            "Bad order number format", status = HttpStatusCode.BadRequest
        )
        val order = orderStorage.find { it.number == id } ?: return@get call.respondText(
            "Not Found",
            status = HttpStatusCode.NotFound
        )
        val total : Double = order.contents.sumOf { it.price * it.amount }
        call.respond(total)
    }
}