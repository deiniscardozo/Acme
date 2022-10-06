package com.example.acme

class Users {
    var id: Int = 0
    var user: String = ""
    var password: String = ""
    constructor(user: String, password: String) {
        this.user = user
        this.password = password
    }
    constructor() {}
}