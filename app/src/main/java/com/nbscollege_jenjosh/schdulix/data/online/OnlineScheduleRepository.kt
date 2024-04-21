package com.nbscollege_jenjosh.schdulix.data.online

import com.nbscollege_jenjosh.schdulix.data.ReminderModelDao
import com.nbscollege_jenjosh.schdulix.data.repository.ScheduleRepository
import com.nbscollege_jenjosh.schdulix.model.AddTimeModel
import com.nbscollege_jenjosh.schdulix.model.AddTimeTmpModel
import com.nbscollege_jenjosh.schdulix.model.ReminderModel
import com.nbscollege_jenjosh.schdulix.model.UserProfile
import com.nbscollege_jenjosh.schdulix.network.HttpRoutes
import com.nbscollege_jenjosh.schdulix.network.KtorClient
import com.nbscollege_jenjosh.schdulix.ui.theme.reminder.ReminderDetails
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpMessage
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import timber.log.Timber

class OnlineScheduleRepository(private val ktorClient: HttpClient = KtorClient()) :
    ScheduleRepository {
    override suspend fun getAllScheduleStream(username: String): Flow<List<ReminderModel>> {
        val cl = ktorClient.request(
            HttpRoutes.login
        ) {
            method = HttpMethod.Post
            url(HttpRoutes.login)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(MultiPartFormDataContent(formData {
                append("type", "get_schedule")
                append("username", username)
            }))

        }
        Timber.i("SAMPLE" + cl.bodyAsText())
        val response = cl.body<ResponseAPI>()
        return flow {
            emit(response.data)
        }
    }

    override suspend fun insertSchedule(schedule: ReminderModel, scheduledtl: List<AddTimeModel>):ResponseAPIDefault {
        val cl = ktorClient.request(
            HttpRoutes.login
        ) {
            method = HttpMethod.Post
            url(HttpRoutes.login)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(MultiPartFormDataContent(formData {
                append("type", "save_schedule")
                append("schedule_head", Json.encodeToString(schedule))
                append("schedule_dtl", Json.encodeToString(scheduledtl))
            }))
        }
        val response = cl.body<ResponseAPIDefault>()
        return response
    }

    override suspend fun insertScheduleTmp(time: AddTimeTmpModel) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteSchedule(username: String, title: String) {
        val cl = ktorClient.request(
            HttpRoutes.login
        ) {
            method = HttpMethod.Post
            url(HttpRoutes.login)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(MultiPartFormDataContent(formData {
                append("type", "delete_schedule")
                append("username", username)
                append("title", title)
            }))
        }
        val response = cl.body<ResponseAPIDefault>()
    }

    override suspend fun updateSchedule(schedule: ReminderModel): ResponseAPIDefault {
        val cl = ktorClient.request(
            HttpRoutes.login
        ) {
            method = HttpMethod.Post
            url(HttpRoutes.login)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(MultiPartFormDataContent(formData {
                append("type", "update_schedule")
                append("username", schedule.createdBy)
                append("dtl", Json.encodeToString(schedule))
            }))

            Timber.i("SAMPLER " + schedule.toString())
        }
        val response = cl.body<ResponseAPIDefault>()
        return response
    }

    override suspend fun getScheduleStream(username: String, title: String): Flow<ReminderModel> {
        val cl = ktorClient.request(
            HttpRoutes.login
        ) {
            method = HttpMethod.Post
            url(HttpRoutes.login)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(MultiPartFormDataContent(formData {
                append("type", "get_schedule_hdr")
                append("username", username)
                append("title", title)
            }))
        }
        val response = cl.body<ResponseAPIHdr>()
        return flow {
            emit(response.data)
        }
    }

    override suspend fun insertScheduleDtl(scheduledtl: List<AddTimeModel>) {
        TODO("Not yet implemented")
    }

    override suspend fun insertScheduleDtl(username: String, scheduledtl: AddTimeModel) {
        val cl = ktorClient.request(
            HttpRoutes.login
        ) {
            method = HttpMethod.Post
            url(HttpRoutes.login)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(MultiPartFormDataContent(formData {
                append("type", "save_schedule_dtl")
                append("username", username)
                append("dtl", Json.encodeToString(scheduledtl))
            }))
        }
        val response = cl.body<ResponseAPIDefault>()
    }

    override suspend fun deleteScheduleDtl(title: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteScheduleDtl(username: String,title: String, id: Int?) {
        val cl = ktorClient.request(
            HttpRoutes.login
        ) {
            method = HttpMethod.Post
            url(HttpRoutes.login)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(MultiPartFormDataContent(formData {
                append("type", "delete_schedule_dtl")
                append("username", username)
                append("title", title)
                append("line", id.toString())
            }))
        }
        val response = cl.body<ResponseAPIDefault>()
    }


    override suspend fun getAllScheduleDtl(username: String, title: String): Flow<List<AddTimeModel>> {
        val cl = ktorClient.request(
            HttpRoutes.login
        ) {
            method = HttpMethod.Post
            url(HttpRoutes.login)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(MultiPartFormDataContent(formData {
                append("type", "get_schedule_dtl")
                append("username", username)
                append("title", title)
            }))
        }
        val response = cl.body<ResponseAPIDtl>()
        return flow {
            emit(response.data)
        }
    }

}

@Serializable
data class ResponseAPI(
    val flag: Int,
    val message: String,
    val data: List<ReminderModel>
)
@Serializable
data class ResponseAPIHdr(
    val flag: Int,
    val message: String,
    val data: ReminderModel
)
@Serializable
data class ResponseAPIDefault(
    val flag: Int,
    val message: String,
)

@Serializable
data class ResponseAPIDtl(
    val flag: Int,
    val message: String,
    val data: List<AddTimeModel>
)