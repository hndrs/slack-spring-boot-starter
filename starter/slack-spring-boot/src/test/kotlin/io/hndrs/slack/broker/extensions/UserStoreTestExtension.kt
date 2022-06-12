package io.hndrs.slack.broker.extensions

import io.hndrs.slack.broker.TestTime
import io.hndrs.slack.broker.store.user.User

fun User.Companion.sample(): User {
    return User(
        "testId",
        "team",
        "",
        false,
        "",
        "",
        "",
        "",
        0,
        User.UserProfile("", "", "", "", "", "", "", "", "", 0, "", false, "", "", "", "", "", "", "", "", "", "", "", "", ""),
        isAdmin = false,
        isOwner = false,
        isPrimaryOwner = false,
        isRestricted = false,
        isUltraRestricted = false,
        isBot = false,
        lastModifiedAt = TestTime.INSTANT,
        isAppUser = false,
        has2fa = false,
        locale = ""
    )
}
