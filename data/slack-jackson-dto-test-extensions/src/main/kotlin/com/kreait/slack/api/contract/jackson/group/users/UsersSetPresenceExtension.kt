package com.kreait.slack.api.contract.jackson.group.users

import com.kreait.slack.api.contract.jackson.UserPresence
import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersSetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.SlackUsersSetPresenceRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersSetPresenceResponse

fun SuccessfulUsersSetPresenceResponse.Companion.sample() = SuccessfulUsersSetPresenceResponse(ok = true)

fun ErrorUsersSetPresenceResponse.Companion.sample() = ErrorUsersSetPresenceResponse(ok = false, error = "")

fun SlackUsersSetPresenceRequest.Companion.sample() = SlackUsersSetPresenceRequest(presence = UserPresence.AUTO)
