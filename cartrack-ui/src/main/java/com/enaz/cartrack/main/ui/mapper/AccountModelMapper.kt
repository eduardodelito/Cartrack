package com.enaz.cartrack.main.ui.mapper

import com.enaz.cartrack.main.db.entity.AccountEntity
import com.enaz.cartrack.main.ui.model.Account

/**
 * Account model mapper.
 *
 * Created by eduardo.delito on 7/26/20.
 */
fun Account.modelToEntityAccount() : AccountEntity {
    return AccountEntity(
        id = 0,
        firstName = firstName,
        lastName = lastName,
        userName = userName,
        password = password
    )
}
