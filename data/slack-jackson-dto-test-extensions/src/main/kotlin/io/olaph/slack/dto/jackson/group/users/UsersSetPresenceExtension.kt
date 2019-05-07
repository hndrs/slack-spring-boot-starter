package io.olaph.slack.dto.jackson.group.users

import io.olaph.slack.dto.jackson.UserPresence

fun SuccessfulUsersSetPresenceResponse.Companion.sample() = SuccessfulUsersSetPresenceResponse(ok = true)

fun ErrorUsersSetPresenceResponse.Companion.sample() = ErrorUsersSetPresenceResponse(ok = false, error = "")

fun SlackUsersSetPresenceRequest.Companion.sample() = SlackUsersSetPresenceRequest(presence = UserPresence.AUTO)