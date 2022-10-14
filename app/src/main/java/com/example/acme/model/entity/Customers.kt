package com.example.acme.model.entity

class Customers {
    var idCustomers: Int? = null
    var customer: String? = null
    var phone: String? = null
    var address: String? = null


    constructor(idCustomers: Int?, customer: String?, phone: String?, address: String?) {
        this.idCustomers = idCustomers
        this.customer = customer
        this.phone = phone
        this.address = address
    }

    constructor() {}
}
