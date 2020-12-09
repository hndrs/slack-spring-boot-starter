package com.kreait.slack.api.contract.jackson.common.types

import com.kreait.slack.api.contract.jackson.common.InstantSample

fun Purpose.Companion.sample() = Purpose(
    value = "",
    createdBy = "",
    lastModifiedAt = InstantSample.sample()
)

