package com.example.plugins

import com.example.model.Person
import com.example.model.customerStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.sql.Connection

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        customerRouting()
    }
}

fun Route.customerRouting() {
    customerStorage.add(Person("0", "Brogrammer", "just everything", 50.0))
    route("/customer") {
        get {
            if (customerStorage.isNotEmpty()) {
                call.respond(customerStorage)
            } else {
                call.respondText("No customers found", status = HttpStatusCode.OK)
            }
        }
        get("{id?}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val customer =
                customerStorage.find { it.id == id } ?: return@get call.respondText(
                    "No customer with id $id",
                    status = HttpStatusCode.NotFound
                )
            call.respond(customer)
        }
        post {
            val customer = call.receive<Person>()
            customerStorage.add(customer)
            call.respondText("success", status = HttpStatusCode.Created)
        }
        delete("{id?}") {

        }
    }
}
