package io.hndrs.slack.api.contract.jackson.common.types

import io.hndrs.slack.api.contract.jackson.common.InstantSample

fun Purpose.Companion.sample() = Purpose(
    value = "",
    createdBy = "",
    lastModifiedAt = InstantSample.sample()
)

