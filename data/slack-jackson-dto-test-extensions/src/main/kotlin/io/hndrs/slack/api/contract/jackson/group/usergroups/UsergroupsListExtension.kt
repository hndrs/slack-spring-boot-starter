package io.hndrs.slack.api.contract.jackson.group.usergroups

fun SuccessfulListResponse.Companion.sample() = SuccessfulListResponse(true, listOf(UserGroup.sample()))

fun ErrorListResponse.Companion.sample() = ErrorListResponse(false, "")

fun ListRequest.Companion.sample() = ListRequest(includeCount = false, includeDisabled = false, includeUsers = false)
