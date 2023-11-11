package com.nbscollege_jenjosh.schdulix.model

import kotlin.system.exitProcess

data class LoginRequest( val username: String, val password: String )
data class LoginResponse( val username: String, val password: String )
data class UserData( val username: String, val password: String, val fullName: String )

fun LoginUser(username:String, password: String): Boolean{
    //val loginReq = LoginRequest(username,password)
    return loginAuth(username,password)
}

fun loginAuth(username:String, password:String): Boolean {
    var result = false
    val userList = listOf(
        UserData(username="jen", password= "jen", fullName = "Jenepir"),
        UserData(username="josh", password= "josh", fullName = "Joseph")
    )

    println(userList)
    userList.forEachIndexed lit@ {index, userData ->
        println("SAMPLELOGIN")
        println(userData)
        println("SAMPLELOGINHERE1")
        println(userList[index].username)
        println("SAMPLELOGINHERE")
        if (!result) {
            if (userData.username == username && userData.password == password) {
                result = true
            }
        }else{
            return@lit
        }
    }
    return result
}