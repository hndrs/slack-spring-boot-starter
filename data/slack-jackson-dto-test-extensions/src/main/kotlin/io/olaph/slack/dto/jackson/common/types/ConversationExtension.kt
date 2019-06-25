package io.olaph.slack.dto.jackson.common.types

fun Conversation.Companion.sample() = Conversation(
        id = "",
        name = "",
        isChannel = true,
        isGroup = false,
        isIm = false,
        created = 1449252889,
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
        lastRead = "",
        isOpen = true,
        priority = 1,
        user = "",
        isUserDeleted = false
)

fun Conversation.Topic.Companion.sample() = Conversation.Topic(
        value = "",
        createdBy = "",
        lastSet = 1
)

fun Conversation.Purpose.Companion.sample() = Conversation.Purpose(
        value = "",
        createdBy = "",
        lastSet = 1
)