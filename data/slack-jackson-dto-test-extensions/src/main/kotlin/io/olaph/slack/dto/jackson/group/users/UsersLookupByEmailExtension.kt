package io.olaph.slack.dto.jackson.group.users

import io.olaph.slack.dto.jackson.common.types.Member
import io.olaph.slack.dto.jackson.common.types.sample

fun SuccessfulUsersLookupByEmailResponse.Companion.sample() = SuccessfulUsersLookupByEmailResponse(true, Member.sample())

fun ErrorUsersLookupByEmailResponse.Companion.sample() = ErrorUsersLookupByEmailResponse(false, "")

fun SlackUsersLookupByEmailRequest.Companion.sample() = SlackUsersLookupByEmailRequest("")