package com.kreait.slack.api.contract.jackson.group.usergroups

import com.kreait.slack.api.contract.jackson.common.types.Channel
import com.kreait.slack.api.contract.jackson.group.channels.sample

fun SuccessfulUsergroupsCreateResponse.Companion.sample() = SuccessfulUsergroupsCreateResponse(true, Usergroup.sample())

fun ErrorUsergroupsCreateResponse.Companion.sample() = ErrorUsergroupsCreateResponse(false, "")

fun SlackUsergroupsCreateRequest.Companion.sample() = SlackUsergroupsCreateRequest(
        "",
        listOf(Channel.sample()),
        "",
        "",
        true
)
