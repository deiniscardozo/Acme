package com.example.acme.model.entity

class Users {
    var id: Int? = null
    var user: String? = null
    var password: String? = null

    constructor(user: String?, password: String?) {
        this.user = user
        this.password = password
    }

    constructor() {}
}