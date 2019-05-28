package io.olaph.slack.dto.jackson.group.usergroups

import io.olaph.slack.dto.jackson.common.types.Usergroup

fun SuccessfulUsergroupsUpdateResponse.Companion.sample() = SuccessfulUsergroupsUpdateResponse(true, Usergroup.sample())

fun ErrorUsergroupsUpdateResponse.Companion.sample() = ErrorUsergroupsUpdateResponse(false, "")

fun SlackUsergroupsUpdateRequest.Companion.sample() = SlackUsergroupsUpdateRequest(
        "",
        listOf(),
        "",
        "",
        false,
        ""
)