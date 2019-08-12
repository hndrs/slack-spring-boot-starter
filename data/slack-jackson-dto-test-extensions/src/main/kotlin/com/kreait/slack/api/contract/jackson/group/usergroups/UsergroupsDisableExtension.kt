package com.kreait.slack.api.contract.jackson.group.usergroups

fun SuccessfulUsergroupsDisableResponse.Companion.sample() = SuccessfulUsergroupsDisableResponse(true, Usergroup.sample())

fun ErrorUsergroupsDisableResponse.Companion.sample() = ErrorUsergroupsDisableResponse(false, "")

fun SlackUsergroupsDisableRequest.Companion.sample() = SlackUsergroupsDisableRequest(Usergroup.sample(), false)
