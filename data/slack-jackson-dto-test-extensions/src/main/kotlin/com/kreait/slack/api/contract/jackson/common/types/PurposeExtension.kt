package com.kreait.slack.api.contract.jackson.common.types

import com.kreait.slack.api.contract.jackson.common.InstantSample

fun Topic.Companion.sample() = Topic(
    value = "",
    createdBy = "",
    lastModifiedAt = InstantSample.sample()
)

