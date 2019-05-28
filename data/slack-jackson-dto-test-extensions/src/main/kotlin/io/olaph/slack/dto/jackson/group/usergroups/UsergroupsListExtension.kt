package io.olaph.slack.dto.jackson.group.usergroups

import io.olaph.slack.dto.jackson.common.types.Usergroup

fun SuccessfulUsergroupsListResponse.Companion.sample() = SuccessfulUsergroupsListResponse(true, listOf(Usergroup.sample()))

fun ErrorUsergroupsListResponse.Companion.sample() = ErrorUsergroupsListResponse(false, "")

fun SlackUsergroupsListRequest.Companion.sample() = SlackUsergroupsListRequest(includeCount = false, includeDisabled = false, includeUsers = false)
