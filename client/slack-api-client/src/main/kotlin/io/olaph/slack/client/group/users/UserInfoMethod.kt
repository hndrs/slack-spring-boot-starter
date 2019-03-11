package io.olaph.slack.client.group.users

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.users.ErrorInfoResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulInfoResponse

@Suppress("UNCHECKED_CAST")
abstract class UsersInfoMethod : ApiCallMethod<UsersInfoMethod, SuccessfulInfoResponse, ErrorInfoResponse, SlackInfoRequest>()

data class SlackInfoRequest(private val userId: String, private val includeLocale: Boolean? = null) {

    fun toRequestMap(): MutableMap<String, String> {
        val requestMap = mutableMapOf<String, String>("user" to userId)
        includeLocale?.let { requestMap.put("include_locale", it.toString()) }
        return requestMap
    }
}
