package io.olaph.slack.client.group.users

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.ChannelType
import io.olaph.slack.dto.jackson.group.users.ErrorUserConversationsResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUserConversationsResponse

@Suppress("UNCHECKED_CAST")
abstract class UserConversationsMethod(private val authToken: String) : ApiCallMethod<UserConversationsMethod, SuccessfulUserConversationsResponse, ErrorUserConversationsResponse, SlackUserChannelListRequest>()

/**
 * DataClass that represents arguments as defined here https://api.slack.com/methods/users.conversations
 */
data class SlackUserChannelListRequest(private val cursor: String? = null,
                                       private val excludeArchived: Boolean? = null,
                                       private val limit: Int? = null,
                                       private val types: Set<ChannelType>? = null,
                                       private val userId: String? = null
) {

    fun toRequestMap(): MutableMap<String, String> {
        val requestMap = mutableMapOf<String, String>()
        cursor?.let { requestMap.put("cursor", it) }
        excludeArchived?.let { requestMap.put("exclude_archived", it.toString()) }
        limit?.let { requestMap.put("limit", it.toString()) }
        types?.let { requestMap.put("types", it.map(ChannelType::value).joinToString(",")) }
        userId?.let { requestMap.put("user", it) }
        return requestMap
    }
}
