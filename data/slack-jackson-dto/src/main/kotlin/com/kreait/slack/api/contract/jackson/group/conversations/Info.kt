package com.kreait.slack.api.contract.jackson.group.conversations

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.common.types.Channel
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulConversationsInfoResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorConversationsInfoResponse::class, name = "false")
)
@JacksonDataClass
sealed class ConversationsInfoResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property channel the channel information
 */
@JacksonDataClass
data class SuccessfulConversationsInfoResponse constructor(override val ok: Boolean,
                                                           @JsonProperty("channel") val channel: Channel)
    : ConversationsInfoResponse(ok) {

    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorConversationsInfoResponse constructor(override val ok: Boolean,
                                                      @JsonProperty("error") val error: String)
    : ConversationsInfoResponse(ok) {

    companion object
}


/**
 * Retrieve information about a conversation.
 *
 * @property channel the channel-id you want to fetch information about
 * @property includeLocale true if locale should be included in the response
 * @property includeNumMembers Set to true to include the member count for the specified conversation.
 */
@JacksonDataClass
data class ConversationsInfoRequest constructor(@JsonProperty("channel") val channel: String,
                                                @JsonProperty("include_locale") val includeLocale: Boolean = false,
                                                @JsonProperty("include_num_members") val includeNumMembers: Boolean = false) {
    companion object

    fun toRequestMap(): MutableMap<String, String> {
        val requestMap = mutableMapOf("channel" to channel)
        requestMap["include_locale"] = includeLocale.toString()
        requestMap["include_num_members"] = includeNumMembers.toString()
        return requestMap
    }
}
