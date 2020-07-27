package com.enaz.cartrack.main.client

import com.enaz.cartrack.main.db.entity.Address
import com.enaz.cartrack.main.db.entity.Company

/**
 * Created by eduardo.delito on 7/27/20.
 */
data class UsersResponse(
    var id: Int,
    var name: String,
    var username: String,
    var email: String,
    var address: Address,
    var phone: String,
    var website: String,
    var company: Company
)
