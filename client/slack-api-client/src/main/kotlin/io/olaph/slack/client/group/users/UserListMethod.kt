package io.olaph.slack.client.group.users

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.users.ErrorUserListResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUserListResponse

@Suppress("UNCHECKED_CAST")
abstract class UserListMethod : ApiCallMethod<UserListMethod, SuccessfulUserListResponse, ErrorUserListResponse, SlackUserListRequest>()

/**
 * DataClass that represents arguments as defined here https://api.slack.com/methods/users.list
 */
data class SlackUserListRequest(private val includeLocale: Boolean? = null,
                                private val limit: Int? = null,
                                private val includePresence: Boolean? = null,
                                private val cursor: String? = null) {

    fun toRequestMap(): MutableMap<String, String> {
        val requestMap = mutableMapOf<String, String>()
        cursor?.let { requestMap.put("cursor", it) }
        includeLocale?.let { requestMap.put("include_locale", it.toString()) }
        limit?.let { requestMap.put("limit", it.toString()) }
        includePresence?.let { requestMap.put("presence", it.toString()) }
        return requestMap
    }
}
