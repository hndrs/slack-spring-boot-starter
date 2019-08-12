package com.kreait.slack.api.contract.jackson.group.usergroups

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.JacksonDataClass

/**
 * https://api.slack.com/methods/usergroups.list
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulUsergroupsListResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorUsergroupsListResponse::class, name = "false")
)

@JacksonDataClass
sealed class UsergroupsListResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulUsergroupsListResponse constructor(override val ok: Boolean,
                                                        @JsonProperty("usergroups") val usergroups: List<Usergroup>)
    : UsergroupsListResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorUsergroupsListResponse constructor(override val ok: Boolean,
                                                   @JsonProperty("error") val error: String)
    : UsergroupsListResponse(ok) {
    companion object
}

data class SlackUsergroupsListRequest(private val includeCount: Boolean?,
                                      private val includeDisabled: Boolean?,
                                      private val includeUsers: Boolean?) {
    companion object

    fun toRequestMap(): MutableMap<String, String> {
        val requestMap = mutableMapOf<String, String>()
        includeCount?.let { requestMap.put("include_count", it.toString()) }
        includeDisabled?.let { requestMap.put("include_disabled", it.toString()) }
        includeUsers?.let { requestMap.put("include_users", it.toString()) }
        return requestMap
    }
}
