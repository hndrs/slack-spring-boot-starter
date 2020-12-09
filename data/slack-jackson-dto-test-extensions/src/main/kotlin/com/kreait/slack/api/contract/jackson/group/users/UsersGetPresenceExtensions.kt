package com.kreait.slack.api.contract.jackson.group.users

import com.kreait.slack.api.contract.jackson.UserPresence

fun SuccessfulGetPresenceResponse.Companion.sample() = SuccessfulGetPresenceResponse(
    ok = true,
    presence = UserPresence.ACTIVE,
    autoAway = true,
    isOnline = true,
    manualAway = false,
    connectionCount = 1,
    lastActivity = ""
)

fun ErrorGetPresenceResponse.Companion.sample() = ErrorGetPresenceResponse(
    ok = false,
    error = ""
)

fun GetPresenceRequest.Companion.sample() = GetPresenceRequest(
    user = ""
)
