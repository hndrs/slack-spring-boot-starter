package com.kreait.slack.api.contract.jackson.common.types

import com.kreait.slack.api.contract.jackson.common.InstantSample

fun Conversation.Companion.sample() = Conversation(
        id = "",
        name = "",
        isChannel = true,
        isGroup = false,
        isIm = false,
        createdAt = InstantSample.sample(),
        createdBy = "",
        isArchived = false,
        isGeneral = true,
        unlinked = 0,
        nameNormalized = "",
        isReadOnly = false,
        isShared = false,
        isExtShared = false,
        isOrgShared = false,
        sharedTeamIds = listOf(),
        pendingShared = listOf(),
        isPendingExtShared = false,
        isMember = true,
        isPrivate = false,
        isMpim = false,
        topic = Conversation.Topic.sample(),
        purpose = Conversation.Purpose.sample(),
        previousNames = listOf(),
        numMembers = 30,
        lastReadAt = InstantSample.sample(),
        isOpen = true,
        priority = 1,
        user = "",
        isUserDeleted = false
)

fun Conversation.Topic.Companion.sample() = Conversation.Topic(
        value = "",
        createdBy = "",
        lastUpdatedAt = InstantSample.sample()
)

fun Conversation.Purpose.Companion.sample() = Conversation.Purpose(
        value = "",
        createdBy = "",
        lastUpdatedAt = InstantSample.sample()
)
