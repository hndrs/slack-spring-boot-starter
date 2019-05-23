package io.olaph.slack.dto.jackson.group.usergroups

import io.olaph.slack.dto.jackson.common.types.Channel
import io.olaph.slack.dto.jackson.common.types.Usergroup
import io.olaph.slack.dto.jackson.group.channels.sample

fun SuccessfulUsergroupsCreateResponse.Companion.sample() = SuccessfulUsergroupsCreateResponse(true, Usergroup.sample())

fun ErrorUsergroupsCreateResponse.Companion.sample() = ErrorUsergroupsCreateResponse(false, "")

fun SlackUsergroupsCreateRequest.Companion.sample() = SlackUsergroupsCreateRequest(
        "SampleUsergroup",
        listOf(Channel.sample()),
        "SampleUsergroupDescription",
        "SampleUsergroupHandle",
        true
)