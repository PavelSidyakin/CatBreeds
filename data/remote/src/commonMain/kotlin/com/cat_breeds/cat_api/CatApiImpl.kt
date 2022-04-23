package com.cat_breeds.cat_api

import com.cat_breeds.cat_api.model.Breed
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal class CatApiImpl : CatApi {

    private val client = HttpClient {
        if (BuildKonfig.CAT_API_KEY.isNotBlank()) {
            headersOf("x-api-key", BuildKonfig.CAT_API_KEY)
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    override suspend fun requestCatBreeds(): List<Breed> {
        return client.get("https://api.thecatapi.com/v1/breeds").body()
    }

    override suspend fun requestCatBreed(id: String): Breed? {
        // There is no optimal request, unfortunately
        return requestCatBreeds().find { it.id == id }
    }
}
