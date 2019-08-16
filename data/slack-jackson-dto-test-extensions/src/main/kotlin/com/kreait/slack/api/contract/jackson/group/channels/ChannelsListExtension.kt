package com.kreait.slack.api.contract.jackson.group.channels

import com.kreait.slack.api.contract.jackson.common.InstantSample
import com.kreait.slack.api.contract.jackson.common.ResponseMetadata
import com.kreait.slack.api.contract.jackson.common.sample
import com.kreait.slack.api.contract.jackson.common.types.Channel
import com.kreait.slack.api.contract.jackson.common.types.Purpose
import com.kreait.slack.api.contract.jackson.common.types.Topic
import com.kreait.slack.api.contract.jackson.common.types.sample
import com.kreait.slack.api.contract.jackson.group.chat.Message
import com.kreait.slack.api.contract.jackson.group.chat.sample

fun SuccessfulChannelListResponse.Companion.sample(): SuccessfulChannelListResponse = SuccessfulChannelListResponse(true, listOf(), ResponseMetadata.sample())

fun ErrorChannelListResponse.Companion.sample(): ErrorChannelListResponse = ErrorChannelListResponse(false, "")

fun Channel.Companion.sample(): Channel = Channel(
        id = "",
        name = "",
        isChannel = true,
        createdAt = InstantSample.sample(),
        isArchived = false,
        isGeneral = true,
        unlinked = 0,
        createdBy = "userID",
        nameNormalized = "",
        isShared = false,
        isOrgShared = false,
        isMember = false,
        isPrivate = false,
        isMpim = false,
        lastReadAt = InstantSample.sample(),
        latest = Message.sample(),
        unreadCount = 0,
        unreadCountDisplay = 0,
        members = listOf(),
        topic = Topic.sample(),
        purpose = Purpose.sample(),
        previousNames = listOf(),
        numMembers = 1
)

