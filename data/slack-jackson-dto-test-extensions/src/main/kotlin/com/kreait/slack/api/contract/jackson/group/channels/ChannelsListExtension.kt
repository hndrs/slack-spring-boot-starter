package com.kreait.slack.api.contract.jackson.group.channels

import com.kreait.slack.api.contract.jackson.common.types.Channel
import com.kreait.slack.api.contract.jackson.group.chat.Message

fun SuccessfulGetChannelListResponse.Companion.sample(): SuccessfulGetChannelListResponse = SuccessfulGetChannelListResponse(true, listOf())

fun ErrorGetChannelListResponse.Companion.sample(): ErrorGetChannelListResponse = ErrorGetChannelListResponse(false, "")

fun Channel.Companion.sample(): Channel = Channel(
        id = "",
        name = "",
        isChannel = true,
        created = 0,
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
        lastRead = "",
        latest = Message.sample(),
        unreadCount = 0,
        unreadCountDisplay = 0,
        members = listOf(),
        topic = Channel.Topic.sample(),
        purpose = Channel.Purpose.sample(),
        previousNames = listOf(),
        numMembers = 1
)


fun Channel.Topic.Companion.sample(): Channel.Topic = Channel.Topic("", "", 0)

fun Channel.Purpose.Companion.sample(): Channel.Purpose = Channel.Purpose("", "", 0)
