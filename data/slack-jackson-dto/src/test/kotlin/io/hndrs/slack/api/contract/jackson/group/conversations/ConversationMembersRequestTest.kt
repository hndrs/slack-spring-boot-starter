package io.hndrs.slack.api.contract.jackson.group.conversations

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class ConversationMembersRequestTest {

    @Test
    @DisplayName("ConversationMemberRequest to request map")
    fun toRequestMap() {

        val channelId = "channelId"
        val cursor = "cursor"
        val limit = 0
        val requestMap = ConversationMembersRequest(channelId, cursor, limit).toRequestMap()

        assertEquals(channelId, requestMap["channel"])
        assertEquals(cursor, requestMap["cursor"])
        assertEquals(limit.toString(), requestMap["limit"])
        assertEquals(3, requestMap.size)

        val minRequestMap = ConversationMembersRequest(channelId).toRequestMap()

        assertEquals(channelId, minRequestMap["channel"])
        assertEquals(1, minRequestMap.size)

    }
}
