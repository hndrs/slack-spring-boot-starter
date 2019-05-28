package io.olaph.slack.dto.jackson.group.usergroups

import io.olaph.slack.dto.jackson.common.types.Usergroup

fun SuccessfulUsergroupsDisableResponse.Companion.sample() = SuccessfulUsergroupsDisableResponse(true, Usergroup.sample())

fun ErrorUsergroupsDisableResponse.Companion.sample() = ErrorUsergroupsDisableResponse(false, "")

fun SlackUsergroupsDisableRequest.Companion.sample() = SlackUsergroupsDisableRequest(Usergroup.sample(), false)