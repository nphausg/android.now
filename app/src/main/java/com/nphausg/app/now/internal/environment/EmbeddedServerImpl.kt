/*
 * Created by nphau on 4/1/23, 1:03 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 4/1/23, 1:02 PM
 */

package com.nphausg.app.now.internal.environment

import android.os.Build
import com.nphausg.app.now.data.BaseResponse
import com.nphausg.app.now.data.Database
import com.nphausg.app.now.data.models.Cart
import com.nphausg.app.now.domain.environment.EmbeddedServer
import com.nphausg.app.now.domain.environment.PORT
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

class EmbeddedServerImpl @Inject constructor() : EmbeddedServer {

    private val server by lazy {
        embeddedServer(Netty, PORT, watchPaths = emptyList()) {
            // configures Cross-Origin Resource Sharing. CORS is needed to make calls from arbitrary
            // JavaScript clients, and helps us prevent issues down the line.
            install(CORS) {
                anyHost()
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
            routing {
                get("/") {
                    call.respondText(
                        text = "Hello!! You are here in ${Build.MODEL}",
                        contentType = ContentType.Text.Plain
                    )
                }
                get("/fruits") {
                    call.respond(HttpStatusCode.OK, BaseResponse(Cart.sample()))
                }
                get("/fruits/{id}") {
                    val id = call.parameters["id"]
                    val fruit = Database.FRUITS.find { it.id == id }
                    if (fruit != null) {
                        call.respond(HttpStatusCode.OK, BaseResponse(fruit))
                    } else {
                        call.respond(HttpStatusCode.NotFound)
                    }
                }
            }
        }
    }

    override fun startServer() {
        CoroutineScope(Dispatchers.IO).launch {
            server.start(wait = true)
        }
    }

    override fun stopServer() {
        server.stop(1_000, 2_000)
    }
}