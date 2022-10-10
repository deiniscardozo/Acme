package com.example.acme.model.entity

class Customers {
    var id: Int? = null
    var customer: String? = null
    var phone: String? = null
    var address: String? = null


    constructor(customer: String?, phone: String?, address: String?) {
        this.customer = customer
        this.phone = phone
        this.address = address
    }

    constructor() {}
}
