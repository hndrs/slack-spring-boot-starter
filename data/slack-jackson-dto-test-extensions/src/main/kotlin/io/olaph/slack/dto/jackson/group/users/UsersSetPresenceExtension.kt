package io.olaph.slack.dto.jackson.group.users

fun SuccessfulUsersSetPresenceResponse.Companion.sample() = SuccessfulUsersSetPresenceResponse(ok = true)

fun ErrorUsersSetPresenceResponse.Companion.sample() = ErrorUsersSetPresenceResponse(ok = false, error = "")

fun SlackUsersSetPresenceRequest.Companion.sample() = SlackUsersSetPresenceRequest(presence = "")