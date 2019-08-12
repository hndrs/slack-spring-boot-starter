package com.kreait.slack.api.contract.jackson.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersLookupByEmailResponse
import com.kreait.slack.api.contract.jackson.group.users.SlackUsersLookupByEmailRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersLookupByEmailResponse
import com.kreait.slack.api.contract.jackson.common.types.Member

fun SuccessfulUsersLookupByEmailResponse.Companion.sample() = SuccessfulUsersLookupByEmailResponse(true, Member.sample())

fun ErrorUsersLookupByEmailResponse.Companion.sample() = ErrorUsersLookupByEmailResponse(false, "")

fun SlackUsersLookupByEmailRequest.Companion.sample() = SlackUsersLookupByEmailRequest("")
