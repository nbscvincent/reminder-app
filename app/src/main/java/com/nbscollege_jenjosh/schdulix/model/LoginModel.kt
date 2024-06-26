package com.nbscollege_jenjosh.schdulix.model

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import kotlin.system.exitProcess

data class LoginRequest( val username: String, val password: String )
data class LoginResponse( val username: String, val password: String )

@Suppress("PLUGIN_IS_NOT_ENABLED")
@Serializable
@Entity(tableName = "user")
data class UserProfile(
    @PrimaryKey val username: String,
    val password: String,
    val firstName: String,
    val lastName: String
)

fun LoginUser(username:String, password: String): Boolean{
    return loginAuth(username,password)
}

var userList = mutableListOf<UserProfile>(
    //UserProfile("jen","jen","Jen","Jabillo")
)
var usernameIndex = 0

fun loginAuth(username:String, password:String): Boolean {
    var result = false

    userList.forEachIndexed stop@ {index, userData ->
        if (!result) {
            if (userData.username == username && userData.password == password) {
                usernameIndex = index
                result = true
            }
        }else{
            return@stop
        }
    }
    return result
}

fun checkLogin(username:String): Boolean {
    var result = false

    userList.forEachIndexed lit@ {index, userData ->
        if (!result) {
            if (userData.username == username) {
                result = true
            }
        }else{
            return@lit
        }
    }
    return result
}