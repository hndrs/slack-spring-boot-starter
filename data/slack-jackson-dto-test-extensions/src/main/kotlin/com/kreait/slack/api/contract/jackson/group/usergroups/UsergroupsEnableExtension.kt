package com.kreait.slack.api.contract.jackson.group.usergroups

fun SuccessfulEnableResponse.Companion.sample() = SuccessfulEnableResponse(true, UserGroup.sample())

fun ErrorEnableResponse.Companion.sample() = ErrorEnableResponse(false, "")

fun EnableRequest.Companion.sample() = EnableRequest("", false)
