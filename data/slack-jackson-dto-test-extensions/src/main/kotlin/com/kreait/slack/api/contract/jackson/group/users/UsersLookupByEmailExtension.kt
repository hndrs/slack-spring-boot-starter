package com.kreait.slack.api.contract.jackson.group.users

import com.kreait.slack.api.contract.jackson.common.types.Member
import com.kreait.slack.api.contract.jackson.common.types.sample

fun SuccessfulUsersLookupByEmailResponse.Companion.sample() = SuccessfulUsersLookupByEmailResponse(true, Member.sample())

fun ErrorUsersLookupByEmailResponse.Companion.sample() = ErrorUsersLookupByEmailResponse(false, "")

fun SlackUsersLookupByEmailRequest.Companion.sample() = SlackUsersLookupByEmailRequest("")
