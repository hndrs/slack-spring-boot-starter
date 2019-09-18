package com.kreait.slack.broker.extensions

import com.kreait.slack.api.contract.jackson.common.InstantSample
import com.kreait.slack.broker.store.user.User

fun User.Companion.sample(): User {
    return User("", "", "", false, "", "", "", "", 0,
            User.UserProfile("", "", "", "", "", "", "", mapOf(), "", "", 0, "", false, "", "", "", "", "", "", "", "", "", "", "", "", ""),
            isAdmin = false, isOwner = false, isPrimaryOwner = false, isRestricted = false, isUltraRestricted = false, isBot = false, lastModifiedAt = InstantSample.sample(), isAppUser = false, has2fa = false, locale = "")
}