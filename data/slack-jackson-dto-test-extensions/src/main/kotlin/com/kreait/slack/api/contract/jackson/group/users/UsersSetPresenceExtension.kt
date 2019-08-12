package com.kreait.slack.api.contract.jackson.group.users

import com.kreait.slack.api.contract.jackson.UserPresence

fun SuccessfulSetPresenceResponse.Companion.sample() = SuccessfulSetPresenceResponse(ok = true)

fun ErrorSetPresenceResponse.Companion.sample() = ErrorSetPresenceResponse(ok = false, error = "")

fun SetPresenceRequest.Companion.sample() = SetPresenceRequest(presence = UserPresence.AUTO)
