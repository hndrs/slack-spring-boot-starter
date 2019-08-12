package com.kreait.slack.api.contract.jackson.group.conversations

import com.kreait.slack.api.contract.jackson.ChannelType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class ConversationListRequestTest {

    @Test
    @DisplayName("ConversationListRequest to request map")
    fun toRequestMap() {

        val cursor = "cursor"
        val excludeArchived = false
        val limit = 0
        val channelTypes = setOf(ChannelType.PUBLIC)
        val requestMap = ConversationsListRequest(cursor, excludeArchived, limit, channelTypes).toRequestMap()

        assertEquals(cursor, requestMap["cursor"])
        assertEquals(excludeArchived.toString(), requestMap["exclude_archived"])
        assertEquals(limit.toString(), requestMap["limit"])
        assertEquals(channelTypes.map(ChannelType::value).joinToString(","), requestMap["types"])
        assertEquals(4, requestMap.size)

        val minRequestMap = ConversationsListRequest(cursor).toRequestMap()

        assertEquals(cursor, minRequestMap["cursor"])
        assertEquals(1, minRequestMap.size)
    }
}
