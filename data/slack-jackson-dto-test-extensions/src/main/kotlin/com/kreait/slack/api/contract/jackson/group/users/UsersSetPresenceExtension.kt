package com.kreait.slack.api.contract.jackson.group.users

import com.kreait.slack.api.contract.jackson.UserPresence

fun SuccessfulUsersSetPresenceResponse.Companion.sample() = SuccessfulUsersSetPresenceResponse(ok = true)

fun ErrorUsersSetPresenceResponse.Companion.sample() = ErrorUsersSetPresenceResponse(ok = false, error = "")

fun SlackUsersSetPresenceRequest.Companion.sample() = SlackUsersSetPresenceRequest(presence = UserPresence.AUTO)
