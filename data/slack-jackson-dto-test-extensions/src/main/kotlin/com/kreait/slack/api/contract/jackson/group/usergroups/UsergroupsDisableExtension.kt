package com.kreait.slack.api.contract.jackson.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorUsergroupsDisableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SlackUsergroupsDisableRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulUsergroupsDisableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.Usergroup
import com.kreait.slack.api.contract.jackson.group.usergroups.sample

fun SuccessfulUsergroupsDisableResponse.Companion.sample() = SuccessfulUsergroupsDisableResponse(true, Usergroup.sample())

fun ErrorUsergroupsDisableResponse.Companion.sample() = ErrorUsergroupsDisableResponse(false, "")

fun SlackUsergroupsDisableRequest.Companion.sample() = SlackUsergroupsDisableRequest(Usergroup.sample(), false)
