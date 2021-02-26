package io.hndrs.slack.api.contract.jackson.group.usergroups

fun SuccessfulDisableResponse.Companion.sample() = SuccessfulDisableResponse(true, UserGroup.sample())

fun ErrorDisableResponse.Companion.sample() = ErrorDisableResponse(false, "")

fun DisableRequest.Companion.sample() = DisableRequest(UserGroup.sample(), false)
