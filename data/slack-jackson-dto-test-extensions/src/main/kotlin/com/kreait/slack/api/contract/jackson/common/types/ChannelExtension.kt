package com.kreait.slack.api.contract.jackson.common.types

import com.kreait.slack.api.contract.jackson.common.InstantSample
import com.kreait.slack.api.contract.jackson.group.chat.Message
import com.kreait.slack.api.contract.jackson.group.chat.sample

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
        latestMessage = Message.sample(),
        unreadCount = 0,
        unreadCountDisplay = 0,
        members = listOf(),
        topic = Topic.sample(),
        purpose = Purpose.sample(),
        previousNames = listOf(),
        numMembers = 1
)
