package io.olaph.slack.dto.jackson.group.users

fun SuccessfulUsersLookupByEmailResponse.Companion.sample() = SuccessfulUsersLookupByEmailResponse(true, User.sample())

fun ErrorUsersLookupByEmailResponse.Companion.sample() = ErrorUsersLookupByEmailResponse(false, "")

fun SlackUsersLookupByEmailRequest.Companion.sample() = SlackUsersLookupByEmailRequest("user@acme.com")