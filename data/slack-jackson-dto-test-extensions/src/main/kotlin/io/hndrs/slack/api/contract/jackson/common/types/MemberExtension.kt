package io.hndrs.slack.api.contract.jackson.common.types

import io.hndrs.slack.api.contract.jackson.common.InstantSample

fun Member.Companion.sample() = Member(
    "", false, "", false, false, false, false, false, false, false, "", "", UserProfile.sample(),
    "", "", "", "", 0, InstantSample.sample(), false, InstantSample.sample()
)
