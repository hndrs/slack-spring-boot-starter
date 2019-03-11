package io.olaph.slack.dto.jackson.group.conversations

import io.olaph.slack.dto.jackson.ChannelType

/**
 * DataClass that represents arguments as defined here https://api.slack.com/methods/conversations.list
 */
data class ConversationsListRequest(private val cursor: String? = null,
                                    private val excludeArchived: Boolean? = null,
                                    private val limit: Int? = null,
                                    private val types: Set<ChannelType>? = null) {

    fun toRequestMap(): MutableMap<String, String> {
        val requestMap = mutableMapOf<String, String>()
        cursor?.let { requestMap.put("cursor", it) }
        excludeArchived?.let { requestMap.put("exclude_archived", it.toString()) }
        limit?.let { requestMap.put("limit", it.toString()) }
        types?.let { requestMap.put("types", it.map(ChannelType::value).joinToString(",")) }
        return requestMap
    }
}
