package com.example.smarthome.data.validation_str

import com.example.smarthome.data.validation_str.TypeStr
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ValidationStr @Inject constructor(){
    fun execute(typeStr: TypeStr):Boolean{
        return when(typeStr){
            is TypeStr.Email -> regEmail(typeStr.str)
            is TypeStr.Password -> regPassword(typeStr.str)
            is TypeStr.Phone -> repPhone(typeStr.str)
            is TypeStr.Username -> regUsername(typeStr.str)
        }
    }
    private fun regEmail(email:String): Boolean{
        val reg = """^[\w-.]+@([\w-]+\.)+[\w-]{2,4}${'$'}"""
        return Regex(reg)
            .matches(email)
//        return Patterns.EMAIL_ADDRESS
//            .matcher(email).find()
    }
    private fun regPassword(password: String) : Boolean{
        val reg = """^[\w-.]{6,}${'$'}"""
        return Regex(reg)
            .matches(password)
    }
    private fun repPhone(phone:String):Boolean{
        val reg = """^(\+)?((\d{2,3}) ?\d|\d)(([ -]?\d)|( ?(\d{2,3}) ?)){5,12}\d${'$'}"""
        return Regex(reg)
            .matches(phone)
//        return Patterns.PHONE
//            .matcher(phone).find()
    }
    private fun regUsername(username:String):Boolean{
        val reg = """^[^\d\s][a-zA-Zа-яА-Я\d-_.]{1,20}${'$'}"""
        return Regex(reg)
            .matches(username)
    }
}