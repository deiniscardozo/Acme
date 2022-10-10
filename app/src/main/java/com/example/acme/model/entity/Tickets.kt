package com.example.acme.model.entity

class Tickets {
    var idTickets: Int? = null
    var work: String? = null
    var dateCreated: String? = null
    var dateSheduled: String? = null
    var note: String? = null
    var distance: String? = null
    var deptClass: String? = null
    var serviceType: String? = null
    var reasonCall: String? = null

    constructor(work: String?, dateCreated: String?, dateSheduled: String?,
                note: String?, distance: String?, deptClass: String?, serviceType: String?,
                reasonCall: String?) {
        this.work = work
        this.dateCreated = dateCreated
        this.dateSheduled = dateSheduled
        this.note = note
        this.distance = distance
        this.deptClass = deptClass
        this.serviceType = serviceType
        this.reasonCall = reasonCall
    }

    constructor() {}
}
