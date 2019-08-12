package com.kreait.slack.api.contract.jackson.group.usergroups

fun SuccessfulUsergroupsEnableResponse.Companion.sample() = SuccessfulUsergroupsEnableResponse(true, Usergroup.sample())

fun ErrorUsergroupsEnableResponse.Companion.sample() = ErrorUsergroupsEnableResponse(false, "")

fun SlackUsergroupsEnableRequest.Companion.sample() = SlackUsergroupsEnableRequest("", false)
