package com.kreait.slack.api.contract.jackson.group.users

import com.kreait.slack.api.contract.jackson.UserPresence

fun SuccessfulUsersGetPresenceResponse.Companion.sample() = SuccessfulUsersGetPresenceResponse(
        ok = true,
        presence = UserPresence.ACTIVE,
        autoAway = true,
        online = true,
        manualAway = false,
        connectionCount = 1,
        lastActivity = "")

fun ErrorUsersGetPresenceResponse.Companion.sample() = ErrorUsersGetPresenceResponse(
        ok = false,
        error = ""
)

fun UsersGetPresenceRequest.Companion.sample() = UsersGetPresenceRequest(
        user = ""
)
