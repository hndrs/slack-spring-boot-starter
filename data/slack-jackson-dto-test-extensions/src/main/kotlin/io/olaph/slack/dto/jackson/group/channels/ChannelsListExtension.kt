package io.olaph.slack.dto.jackson.group.channels

import io.olaph.slack.dto.jackson.group.chat.Message
import io.olaph.slack.dto.jackson.group.chat.sample

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
        creator = "",
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
        topic = Topic.sample(),
        purpose = Purpose.sample(),
        previousNames = listOf(),
        numMembers = 1
)


fun Topic.Companion.sample(): Topic = Topic("", "", 0)

fun Purpose.Companion.sample(): Purpose = Purpose("", "", 0)
