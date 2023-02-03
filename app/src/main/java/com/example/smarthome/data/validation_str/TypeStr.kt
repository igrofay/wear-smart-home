package com.example.smarthome.data.validation_str

sealed class TypeStr{
    class Email(val str:String): TypeStr()
    class Password(val str: String): TypeStr()
    class Phone(val str: String): TypeStr()
    class Username(val str: String): TypeStr()
}
