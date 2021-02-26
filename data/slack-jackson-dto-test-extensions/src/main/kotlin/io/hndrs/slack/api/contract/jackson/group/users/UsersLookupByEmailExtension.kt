package io.hndrs.slack.api.contract.jackson.group.users

import io.hndrs.slack.api.contract.jackson.common.types.Member
import io.hndrs.slack.api.contract.jackson.common.types.sample

fun SuccessfulLookupByEmailResponse.Companion.sample() = SuccessfulLookupByEmailResponse(true, Member.sample())

fun ErrorLookupByEmailResponse.Companion.sample() = ErrorLookupByEmailResponse(false, "")

fun LookupByEmailRequest.Companion.sample() = LookupByEmailRequest("")
