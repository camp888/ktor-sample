package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import kotlinx.html.*

fun Application.configureTemplating() {
    routing {
        get("/html-dsl") {
            call.respondHtml {
                body {
                    h1 { +"HTML" }
                    img(src = "https://upload.wikimedia.org/wikipedia/commons/3/38/Stranger_Things_logo.png")
                }
            }
        }
    }
}
