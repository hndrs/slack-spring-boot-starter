package io.olaph.slack.dto.jackson.group.usergroups

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