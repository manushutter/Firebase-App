package com.r19.myfirebasedatabaseapp

class User {
    var name:String = ""
    var email:String = ""
    var id_number:String = ""
    var id:String = ""


    constructor(userName:String,userEmail:String,userIdNumber:String,id:String){
        this.name = userName
        this.email = userEmail
        this.id_number = userIdNumber
        this.id = id
    }
    //Because we are using firebase ,we need an extra empty constructor

    constructor(){}
}