package com.nbscollege_jenjosh.schdulix.data.online

import com.nbscollege_jenjosh.schdulix.data.ReminderModelDao
import com.nbscollege_jenjosh.schdulix.data.repository.ScheduleRepository
import com.nbscollege_jenjosh.schdulix.model.AddTimeModel
import com.nbscollege_jenjosh.schdulix.model.AddTimeTmpModel
import com.nbscollege_jenjosh.schdulix.model.ReminderModel
import com.nbscollege_jenjosh.schdulix.model.UserProfile
import com.nbscollege_jenjosh.schdulix.network.HttpRoutes
import com.nbscollege_jenjosh.schdulix.network.KtorClient
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
import timber.log.Timber

class OnlineScheduleRepository(private val ktorClient: HttpClient = KtorClient()) :
    ScheduleRepository {
    override suspend fun getAllScheduleStream(username: String): Flow<List<ReminderModel>> {
        Timber.i("SAMPLE STREAM")
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
        Timber.i("SAMPLE STREAM ${cl.bodyAsText()}")

        val response = cl.body<ResponseAPI>()
        return flow {
            emit(response.data)
        }
    }

    override suspend fun insertSchedule(schedule: ReminderModel) {
        TODO("Not yet implemented")
    }

    override suspend fun insertScheduleTmp(time: AddTimeTmpModel) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteSchedule(user: ReminderModel) {
        TODO("Not yet implemented")
    }

    override suspend fun updateSchedule(schedule: ReminderModel) {
        TODO("Not yet implemented")
    }

    override fun getScheduleStream(title: String): Flow<ReminderModel> {
        TODO("Not yet implemented")
    }

    override suspend fun insertScheduleDtl(scheduledtl: List<AddTimeModel>) {
        TODO("Not yet implemented")
    }

    override suspend fun insertScheduleDtl(scheduledtl: AddTimeModel) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteScheduleDtl(title: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteScheduleDtl(id: Int?) {
        TODO("Not yet implemented")
    }

    override fun getAllScheduleDtl(title: String): Flow<List<AddTimeModel>> {
        TODO("Not yet implemented")
    }

}

@Serializable
data class ResponseAPI(
    val flag: Int,
    val message: String,
    val data: List<ReminderModel>
)