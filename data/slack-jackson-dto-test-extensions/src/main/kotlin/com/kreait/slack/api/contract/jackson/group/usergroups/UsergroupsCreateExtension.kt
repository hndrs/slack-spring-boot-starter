package com.kreait.slack.api.contract.jackson.group.usergroups

import com.kreait.slack.api.contract.jackson.common.types.Channel
import com.kreait.slack.api.contract.jackson.common.types.sample

fun SuccessfulCreateResponse.Companion.sample() = SuccessfulCreateResponse(true, UserGroup.sample())

fun ErrorCreateResponse.Companion.sample() = ErrorCreateResponse(false, "")

fun CreateRequest.Companion.sample() = CreateRequest(
    "",
    listOf(Channel.sample()),
    "",
    "",
    true
)
