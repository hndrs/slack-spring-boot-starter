package io.hndrs.slack.api.contract.jackson.common.types

import io.hndrs.slack.api.contract.jackson.common.InstantSample

fun Topic.Companion.sample() = Topic(
    value = "",
    createdBy = "",
    lastModifiedAt = InstantSample.sample()
)

