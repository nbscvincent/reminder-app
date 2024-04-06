package com.nbscollege_jenjosh.schdulix.data.online

import com.nbscollege_jenjosh.schdulix.data.UserProfileDao
import com.nbscollege_jenjosh.schdulix.data.repository.UserRepository
import com.nbscollege_jenjosh.schdulix.model.UserProfile
import com.nbscollege_jenjosh.schdulix.network.HttpRoutes
import com.nbscollege_jenjosh.schdulix.network.KtorClient
import io.ktor.client.HttpClient
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import kotlinx.coroutines.flow.Flow

import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.readText
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.serialization.json.Json
import timber.log.Timber

class OnlineUserRepository(private val ktorClient: HttpClient = KtorClient() ) : UserRepository {
    override fun getAllUsersStream(): Flow<List<UserProfile>> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserStream(id: String): Flow<UserProfile?>  {
        val cl = ktorClient.request(
            HttpRoutes.login
        ) {
            method = HttpMethod.Post
            url(HttpRoutes.login)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(MultiPartFormDataContent(formData {
                append("type", "check_login")
                append("username", id)
            }))
        }
        return flow {
            emit(cl.body())
        }
    }

    // login
    override suspend fun getUserPasswordStream(username: String, password: String): Flow<UserProfile?> {
        val cl = ktorClient.request(
            HttpRoutes.login
        ) {
            method = HttpMethod.Post
            url(HttpRoutes.login)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(MultiPartFormDataContent(formData {
                append("type", "login")
                append("username", username)
                append("password", password)
            }))
        }
        return flow {
            emit(cl.body())
        }
    }
        // end login
    override suspend fun insertUser(user: UserProfile) { TODO("Not yet implemented") }
    override suspend fun deleteUser(user: UserProfile) { TODO("Not yet implemented") }
    override suspend fun updateUser(user: UserProfile) { TODO("Not yet implemented") }
}
