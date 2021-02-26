package io.hndrs.slack.api.contract.jackson.group.usergroups

fun SuccessfulUpdateResponse.Companion.sample() = SuccessfulUpdateResponse(true, UserGroup.sample())

fun ErrorUpdateResponse.Companion.sample() = ErrorUpdateResponse(false, "")

fun UpdateRequest.Companion.sample() = UpdateRequest(
    "",
    listOf(),
    "",
    "",
    false,
    ""
)
